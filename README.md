# spring-gift-enhancement

## 0️⃣ 0단계 - 기본 코드 준비
- 위시 리스트 코드를 옮겨 온다.

## 1️⃣ 1단계 - 엔티티 매핑
### 구현할 기능 목록
- [x] Member 엔티티 클래스 작성
- [x] Product 엔티티 클래스 작성
- [x] Wish 엔티티 클래스 작성
  - [x] 엔티티 간 연관관계 매핑
- [x] Spring Data JPA 기반 Repository 작성
- [x] @DataJpaTest를 활용한 레포지토리 테스트 코드 작성

## 2️⃣ 단계 - 페이지네이션
### 구현할 기능 목록
- [ ] 상품 보기에 페이지네이션 구현
- [ ] 위시 리스트 보기에 페이지네이션 구현

## 질문 사항

### 1. 
- 프로그래밍 요구사항 중, "규칙 3: 모든 원시 값과 문자열을 포장한다." 가 있다고 하셨는데, 예를 들어 다음과 같이 모든 String이나 Long 같은 값들에 대해서도,

```
@Column(nullable = false)
private String name;

@Column(nullable = false)
private Long price;

@Column(nullable = false)
private String imageUrl;
```
이런 값들을 클래스로 포장해서, 
```
public class ProductName {
    private final String value;
    // 생성자에서 유효성 검증 등
}

public class Product {
    private ProductName name;

    public Product(ProductName name, ...) {
        this.name = name;
    }
}
```
- 이런 식으로 사용하는게 맞는 걸까요?
- 또 만약 이 방식이 맞다면, 모든 원시값과 문자열을 클래스로 포장하게 되면 객체 수가 많아지고, 결과적으로 객체 간 의존도가 지나치게 높아지는 건 아닌가요?
