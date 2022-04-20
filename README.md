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
