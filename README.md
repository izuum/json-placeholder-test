
# Allure-отчет о тестировании доступен по ссылке:
https://izuum.github.io/json-placeholder-test/

# БАГ
#TC-068 CreatePostWithInvalidJsonPayloadTest содержит баг:
-
- при отправке запроса с невалидным JSON в ответ приходит статус-код 500(Internal server error) вместо 400(Bad request)
- при отправке запроса с пустым телом сервер создает ресурс, статус-код 201(Created), присваивает ему id(что тоже можно считать багом)
  



