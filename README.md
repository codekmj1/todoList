1. 수정, 삭제 API의 request를 어떤 방식으로 사용 하셨나요? (param, query, body)
  1) 수정 API
   - @PathVariable id: URL의 일부로서 전달되는 'id' 파라미터. 이는 수정할 할 일 리스트의 고유 ID를 지정합니다.
   - @RequestBody todoListDTO: HTTP 요청 본문에 포함된 JSON 데이터. 이는 수정하려는 할 일 리스트의 새로운 내용을 담고 있습니다.
  2) 삭제 API
   - @PathVariable id: URL의 일부로서 전달되는 'id' 파라미터. 이는 삭제할 할 일 리스트의 고유 ID를 지정합니다.
 
2. RESTful한 API를 설계하셨나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?
RESTful한 API 설계 부분
  1) 자원 중심 설계: 이 코드는 할 일 목록(TodoList)이라는 특정 자원에 중점을 두고 설계되었습니다. 각 HTTP 메소드 (GET, POST, PUT, DELETE)는 이 자원에 대한 특정 작업을 나타냅니다.
  2) 상태 전환을 통한 상태 변경: 클라이언트는 서버의 자원 상태를 변경하기 위해 HTTP 메소드를 사용합니다. 예를 들어, POST 메소드로 새로운 할 일을 생성하고, PUT 메소드로 기존의 할 일을 수정하며, DELETE 메소드로 할 일을 삭제할 수 있습니다.
  3) Stateless: 이 서비스는 stateless입니다. 즉, 각 요청은 서버에서 어떤 상태도 유지하지 않으며, 각 요청은 모든 필요한 정보를 가지고 있어야 합니다.
  4) 적절한 HTTP 상태 코드 사용: 이 API는 응답으로 적절한 HTTP 상태 코드를 반환합니다. 예를 들어, 새로운 자원이 생성되면 201(CREATED) 상태 코드를, 자원이 성공적으로 삭제되면 204(NO_CONTENT) 상태 코드를 반환합니다.
RESTful하지 않은 부분
  1) HATEOAS(Hypertext As The Engine Of Application State): 이 원칙에 따르면, 응답은 클라이언트가 애플리케이션의 상태를 진행시키는 데 필요한 모든 정보를 제공해야 합니다. 즉, 응답에는 다음 작업을 위한 링크 정보도 포함되어야 합니다. 하지만 이 코드에서는 그러한 링크를 제공하고 있지 않습니다.
  2) Versioning: API의 버전 관리. API의 변경 사항을 관리하기 위해 일반적으로 URI에 버전 정보를 포함시킵니다. 이 코드에서는 API의 버전 정보가 없습니다.
  3) Error Handling: 예외 처리는 있지만, 클라이언트에게 어떤 오류가 발생했는지 더 구체적인 정보를 제공하는 것이 부족합니다. 이 코드에서는 IllegalArgumentException이 발생했을 때 단순히 메시지를 반환하고 있습니다.
   
3. 적절한 관심사 분리를 적용하셨나요? (Controller, Service, Repository)
  1) Controller: HTTP 요청을 받아 서비스 로직을 호출하고, 서비스 로직의 결과를 클라이언트에게 응답합니다. TodoListController 클래스에서 이 부분이 잘 수행되고 있습니다.
  2) Service: 비즈니스 로직을 수행합니다. TodoListService와 그 구현체인 TodoListServiceImpl에서 이 부분이 잘 수행되고 있습니다.
  3) Repository: 데이터베이스와 상호작용을 담당합니다. TodoListRepository 인터페이스에서 이 부분이 잘 수행되고 있습니다.
   
4. API 명세서 작성 가이드라인을 검색하여 직접 작성한 API 명세서와 비교해보세요!
   

   
