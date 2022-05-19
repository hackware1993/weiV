package cn.flutterfirst.weiv.core.others

@Retention(AnnotationRetention.SOURCE)
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FIELD,
    AnnotationTarget.EXPRESSION
)
annotation class KotlinOnly
