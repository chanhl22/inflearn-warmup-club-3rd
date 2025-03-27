# 미션 - Day18

* [1. @Mock, @MockBean, @Spy, @SpyBean, @InjectMocks 의 차이를 한번 정리해 봅시다.](#1-mock-mockbean-spy-spybean-injectmocks-의-차이를-한번-정리해-봅시다)
* [2. 각 항목을 @BeforeEach, given절, when절에 배치한다면 어떻게 배치하고 싶으신가요?
  (@BeforeEach에 올라간 내용은 공통 항목으로 합칠 수 있습니다. ex. 1-1과 2-1을 하나로 합쳐서 @BeforeEach에 배치)](#2-각-항목을-beforeeach-given절-when절에-배치한다면-어떻게-배치하고-싶으신가요-beforeeach에-올라간-내용은-공통-항목으로-합칠-수-있습니다-ex-1-1과-2-1을-하나로-합쳐서-beforeeach에-배치)
---
### 1. @Mock, @MockBean, @Spy, @SpyBean, @InjectMocks 의 차이를 한번 정리해 봅시다.
1. @Mock
    * Mockito에서 제공하는 어노테이션으로, 테스트에서 사용할 가짜 객체(mock)를 생성합니다.
    * 주로 단위 테스트에서 의존성을 대체하기 위해 사용됩니다.
2. @MockBean
    * Spring Boot의 테스트에서 사용되는 어노테이션으로, Spring ApplicationContext에 mock 객체를 등록합니다.
    * 주로 통합 테스트에서 사용되며, Spring의 의존성 주입을 통해 mock 객체를 주입받을 수 있습니다.
    * @SpringBootTest와 함께 사용됩니다.
3. @Spy
    * 한 객체에서 일부는 실제 객체의 기능을 쓰고 싶고 일부만 stubbing 을 하고 싶을 때 @Spy 를 사용합니다.
4. @SpyBean
    * @MockBean과 유사하지만, 실제 객체의 메서드를 호출할 수 있는 spy 객체를 생성합니다.
5. @InjectMocks
   * 테스트 대상 클래스의 생성자, 필드, 또는 setter 메서드를 통해 mock 객체를 자동으로 주입합니다.
   * 주로 단위 테스트에서 사용됩니다.

<br>

### 2. 각 항목을 @BeforeEach, given절, when절에 배치한다면 어떻게 배치하고 싶으신가요? (@BeforeEach에 올라간 내용은 공통 항목으로 합칠 수 있습니다. ex. 1-1과 2-1을 하나로 합쳐서 @BeforeEach에 배치)

```java
@BeforeEach 
void setUp() {
    1-1. 2-1. 3-1. 사용자 생성에 필요한 내용 준비
    1-2. 2-2. 3-2. 사용자 생성
    1-3. 2-3. 3-5. 게시물 생성에 필요한 내용 준비
    1-4. 2-4. 3-6. 게시물 생성
} 

@DisplayName("사용자가 댓글을 작성할 수 있다.")
@Test
void writeComment() {
    // given
    1-5. 댓글 생성에 필요한 내용 준비
            
    // when
    1-6. 댓글 생성

    // then
    검증
}

@DisplayName("사용자가 댓글을 수정할 수 있다.")
@Test
void updateComment() {
    // given
    2-5. 댓글 생성에 필요한 내용 준비
    2-6. 댓글 생성

    // when
    2-7. 댓글 수정
            
    // then
    검증
}

@DisplayName("자신이 작성한 댓글이 아니면 수정할 수 없다.")
@Test
void cannotUpdateCommentWhenUserIsNotWriter() {
    // given
    3-3. 사용자2 생성에 필요한 내용 준비
    3-4. 사용자2 생성
    3-7. 사용자1의 댓글 생성에 필요한 내용 준비
    3-8. 사용자1의 댓글 생성

    // when
    3-9. 사용자2가 사용자1의 댓글 수정 시도

    // then
    검증
}
```
