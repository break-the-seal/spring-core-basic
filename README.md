# Spring Core Basic

## Note

- Spring Container에서 부모 클래스를 가져오면 그에 해당하는 자식 클래스(구현 클래스) 모두 가져오게 된다.
  - Object class를 Container에서 가져오게 되면 Container에 등록된 모든 bean들을 가져온다.
- `@Configuration`
  - config 코드에서 해당 어노테이션을 등록하면 CGLIB 바이트 코드 조작 라이브러리를 통해   
    config 파일을 상속받은 다른 클래스를 만들고 해당 클래스를 bean으로 등록한다.
  - `AppConfig` > `AppConfig$$CGLIB`(bean 등록)
  - CGLIB로 만들어진 Config 파일에서 Container에 등록된 빈들을 싱글톤으로 관리하도록 해준다.
  - `@Configuration` 없이 `@Bean`만 등록하는 경우 싱글톤을 보장하지 않는다.

## 의존관계 자동 주입

### 조회 빈 2개 이상인 경우
- `@Autowired`: 타입 매칭이후 필드명으로 조회하게 된다.
- `@Qualifier`: 추가 구분자 (빈 이름과 별개)
- `@Primary`: 우선 선택 대상 지정

### 어노테이션 직접 만들
- `@Qualifier`를 문자열로 지정하는 것은 컴파일 단계에서 리스크가 있기 때문에 따로 어노테이션 만들어서 사용가능

### 조회한 빈이 모두 필요할 때
- `AllBeanTest.kt` 참고
```kotlin
private val policyMap: Map<String, DiscountPolicy>
private val policies: List<DiscountPolicy>
```

### 자동, 수동 빈 등록의 올바른 실무 운영 기준
- 자동 빈 등록
  - Controller, Service, Repository 같은 업무 로직 빈
- 수동 빈 등록
  - 기술 지원 빈 (DB, Redis 등의 설정)
- 다형성을 적극 활용하는 비즈니스 로직은 수동 등록을 고민해보자  
(워낙 빈들이 많아서)