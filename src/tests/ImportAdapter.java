package tests;

import java.math.BigDecimal;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import org.jsweet.transpiler.ModuleImportDescriptor;
import org.jsweet.transpiler.extension.PrinterAdapter;
import org.jsweet.transpiler.model.CompilationUnitElement;
import org.jsweet.transpiler.model.ImportElement;

public class ImportAdapter extends PrinterAdapter {

	public ImportAdapter(PrinterAdapter parentAdapter) {
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
		if (importedClass.getQualifiedName().toString().startsWith("java.") && !BigDecimal.class.getName().equals(importedClass.getQualifiedName().toString())) {
			return new ModuleImportDescriptor(true, currentCompilationUnit.getPackage(), importedClass.getSimpleName().toString(), importedClass.getQualifiedName().toString());
		}
		
		return super.getModuleImportDescriptor(currentCompilationUnit, importedName, importedClass);
	}

}
