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

## 빈 생명주기 콜백

- 스프링 빈의 이벤트 라이프사이클
```text
스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> *초기화 콜백* -> 사용 -> *소멸전 콜백* -> 스프링 종료
```

- 객체의 생성과 초기화를 분리
  - 객체 생성: 필수 파라미터를 받고 메모리를 할당해 객체 생성
  - 초기화: 생성된 값을 가지고 외부 커낵션 연결하는 등의 무거운 작업 수행

### 인터페이스 활용
- InitializingBean, DisposableBean
  - afterPropertiesSet(), destroy()
- 단점
  - 스프링 전용 인터페이스 (인터페이스에 의존적)
  - 초기화, 소멸 메소드 이름 변경 불가
  - 외부 라이브러리에 이것을 사용하고 있다면 이에 대한 수정은 불가(무조건 사용해야 함)
- **거의 사용하지 않는 방법임**

## Bean 등록시 초기화, 소멸 메소드 등록
```kotlin
@Bean(initMethod = "init", destroyMethod = "close")
```
- initMethod, destroyMethod 따로 지정가능
- 스프링 인터페이스에 의존적이지 않게 됨
- destroyMethod 기본값이 `INFER_METHOD` 로 되어 있는데   
close, shutdown 같은 일반적인 destroyMethod name을 자동적으로 소멸 메소드로 등록하게 된다.  
  (주의해야 함)

## 어노테이션 방식
- `@PostConstruct`, `@PreDestroy`
- 스프링에서 권장하는 방법
- 스프링 종속된 기술이 아닌 `JSR-250` 자바 표준 기술이다.
- 외부 라이브러리에 적용 못 (2번째 방법 사용해야함)
