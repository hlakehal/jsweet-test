package tests;

import java.math.BigDecimal;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import org.jsweet.transpiler.ModuleImportDescriptor;
import org.jsweet.transpiler.extension.PrinterAdapter;
import org.jsweet.transpiler.model.CompilationUnitElement;
import org.jsweet.transpiler.model.ImportElement;

public class TestAdapter extends PrinterAdapter {

	public TestAdapter(PrinterAdapter parentAdapter) {
		super(parentAdapter);
	}

	@Override
	public String needsImport(ImportElement importElement, String qualifiedName) {
		String s = super.needsImport(importElement, qualifiedName);
		if (importElement.getImportedType().getKind() == ElementKind.ANNOTATION_TYPE) {
			return null;
		}
		if (qualifiedName.startsWith("java.") && !BigDecimal.class.getName().equals(qualifiedName)) {
			return qualifiedName;
		}
		return s;
	}

	@Override
	public ModuleImportDescriptor getModuleImportDescriptor(CompilationUnitElement currentCompilationUnit,
			String importedName, TypeElement importedClass) {
		if (importedClass.getKind() == ElementKind.ANNOTATION_TYPE) {
			return null;
		}
		if (BigDecimal.class.getName().equals(importedClass.toString())) {
			return new ModuleImportDescriptor(currentCompilationUnit.getPackage(), "Big", "big.js");
		}
		return super.getModuleImportDescriptor(currentCompilationUnit, importedName, importedClass);
	}

}
