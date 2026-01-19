2026-01-15T22:37:29.819+09:00 WARN 19373 --- [sb-ecom] [nio-8080-exec-2]
.w.s.m.s.DefaultHandlerExceptionResolver : Resolved [
org.springframework.web.bind.MethodArgumentNotValidException: Validation failed for argument [0] in
public org.springframework.http.ResponseEntity<java.lang.String>
com.ecommerce.project.controller.CategoryController.createCategory(
com.ecommerce.project.model.Category): [Field error in object 'category' on field 'categoryName':
rejected value [];
codes [NotBlank.category.categoryName,NotBlank.categoryName,NotBlank.java.lang.String,NotBlank];
arguments [org.springframework.context.support.DefaultMessageSourceResolvable:
codes [category.categoryName,categoryName]; arguments []; default message [categoryName]]; default
message [공백일 수 없습니다]] ]


---
MethodArgumentNotValidException - categoryName
