package tests;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.jsweet.transpiler.extension.AnnotationManager;
import org.jsweet.transpiler.extension.PrinterAdapter;

import jsweet.lang.Erased;

public class StubAdapter extends PrinterAdapter {

	public StubAdapter(PrinterAdapter parentAdapter) {
		super(parentAdapter);
		addAnnotationManager(new AnnotationManager() {
			@Override
			public Action manageAnnotation(Element element, String annotationType) {
				if (Erased.class.getName().equals(annotationType)) {
					if (element instanceof TypeElement && element.getAnnotation(Path.class) != null) {
						return Action.ADD;
					}
				}
				return Action.VOID;
			}
		});
	}

	@Override
	public void afterType(TypeElement type) {
		Path path = type.getAnnotation(Path.class);
		if (path != null) {
			getPrinter().getContext().addHeader("stub." + type.toString(),
					"import { path as Path, get as Get, queryParam as QueryParam } from './decorators/ws.annotation.api';");
			print("@Path('" + path.value() + "')").println();
			print("export class " + type.getSimpleName() + " {").println();
			getPrinter().startIndent();
			for (Element e : type.getEnclosedElements()) {
				if (e instanceof ExecutableElement) {
					ExecutableElement method = (ExecutableElement) e;
					path = method.getAnnotation(Path.class);
					if (path != null) {
						printIndent().print("@Path('" + path.value() + "')").println();
					}
					GET get = method.getAnnotation(GET.class);
					if (get != null) {
						printIndent().print("@Get()").println();
					}
					POST post = method.getAnnotation(POST.class);
					if (post != null) {
						printIndent().print("@Post()").println();
					}
					// TODO: support other JAXRS annotations
					printIndent().print("public " + method.getSimpleName() + "(").println();
					getPrinter().startIndent();
					for (VariableElement p : method.getParameters()) {
						QueryParam qp = p.getAnnotation(QueryParam.class);
						if (qp != null) {
							printIndent().print("@QueryParam('" + qp.value() + "') ");
						}
						print(p.getSimpleName()).print(" : ").print(getMappedType(p.asType()));
						print(",").println();
					}

					if (!method.getParameters().isEmpty()) {
						getPrinter().removeLastChars(2);
					}
					print(") : ").print(getMappedType(method.getReturnType())).print(" {").println();
					if (method.getReturnType().toString() != "void") {
						printIndent().print("return null;").println();
					}
				}
			}
			getPrinter().endIndent();
			printIndent().print("}").println();
			getPrinter().endIndent();
			printIndent().print("}").println();
		}
		super.afterType(type);
	}

}
