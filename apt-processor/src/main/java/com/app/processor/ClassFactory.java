package com.app.processor;


import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

/**
 * @author：xinyu.zhou
 * @version: 2018/9/18 15:06
 * @ClassName:
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 */
public class ClassFactory {
    private String mBindingClassName;
    private String mPackageName;
    private TypeElement mTypeElement;
    private Map<Integer, VariableElement> mVariableElementMap = new HashMap<>();

    public ClassFactory(Elements elementUtils, TypeElement classElement) {
        this.mTypeElement = classElement;
        PackageElement packageElement = elementUtils.getPackageOf(mTypeElement);
        String packageName = packageElement.getQualifiedName().toString();
        String className = mTypeElement.getSimpleName().toString();
        this.mPackageName = packageName;
        this.mBindingClassName = className + "_ViewBinding";
    }

    public void putElement(int id, VariableElement element) {
        mVariableElementMap.put(id, element);
    }
    /**
     * 创建Java代码
     * javapoet
     *
     * @return
     */
    public TypeSpec generateJavaCode() {
        TypeSpec bindingClass = TypeSpec.classBuilder(mBindingClassName)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(generateMethods())
                .build();
        return bindingClass;

    }

    /**
     * 加入Method
     * javapoet
     */
        private MethodSpec generateMethods() {
        ClassName activity = ClassName.bestGuess(mTypeElement.getQualifiedName().toString());
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("bind")
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addParameter(activity, "activity");

        for (int id : mVariableElementMap.keySet()) {
            VariableElement element = mVariableElementMap.get(id);
            String name = element.getSimpleName().toString();
            String type = element.asType().toString();

            methodBuilder.addCode("activity." + name + " = " + "(" + type + ")(((android.app.Activity)activity).findViewById( " + id + "));\n\n");
        }

        return methodBuilder.build();
    }


    public String getPackageName() {
        return mPackageName;
    }
}