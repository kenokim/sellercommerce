### API Documentation - swagger
http://localhost:8080/swagger-ui/index.html


# 실행 방법
(ubuntu) ./gradlew build -x test && java -jar ./build/libs/sellercommerce-0.0.1-SNAPSHOT.jar
<br>
(브라우저) http://localhost:8080/swagger-ui/index.html
<br>







### Batch
H2 DB 를 사용할 경우, Batch table 이 자동으로 생성됩니다.

### Rest API
 Swagger-ui 에 -help 가 표시된 API 는 요구사항 외에 편의로 만든 API 입니다.
 계약, 업체, 광고 도메인이 존재하며 각각은 명세에 적힌 내용을 수행합니다. 자세한 작동 내용은 각각의 컨트롤러에 주석으로 적어놓았습니다.
 
### 사용한 오픈소스 및 라이브러리
QueryDsl - 동적 쿼리를 편리하게 작성하기 위해 사용했습니다.
<br>
Lombok - 자바 코드를 편리하게 작성하기 위해 사용했습니다.