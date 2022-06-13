
# 실행 방법
### Linux
(Command Line)  ./gradlew build -x test && java -jar ./build/libs/sellercommerce-0.0.1-SNAPSHOT.jar
<br>
(브라우저)  http://localhost:8080/swagger-ui/index.html
<br>

# 환경 설정
application.properties 에 h2 test 데이터베이스를 설정하였으므로, 데이터베이스 관련 추가 설정이 필요하지 않습니다.

 
# 사용한 오픈소스 및 라이브러리
QueryDsl - 동적 쿼리를 편리하게 작성하기 위해 사용했습니다.
<br>
Lombok - 자바 코드를 편리하게 작성하기 위해 사용했습니다.

