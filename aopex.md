AOP관련 연습(AOP적용 하기위한 준비)
===
### 1. 관련 라이브러리 다운로드(pom.xml)
        <!-- aop관련 라이브러리 -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-aop</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
		<!-- 트랜잭션 처리 라이브러리 -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-tx</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
		<!-- AspectJ -->
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>${org.aspectj-version}</version>
		</dependency>
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjtools</artifactId>
			<version>${org.aspectj-version}</version>
		</dependency>

### 2. root-context
      <!-- Proxy객체 생성을 위해서 잡아줌 -->
	<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
	 <!--aop 빈 객체 자동생성  -->
	<context:component-scan base-package="com.overware.aop"></context:component-scan>

### 3, 예제용 데이터 베이스 생성
      create table tbl_user(
          uid varchar(50),
          upw varchar(50),
          uname varchar(100),
          upoint int(11),
          primary key (uid)
      );

      create table tbl_message(
          mno int not null auto_increment,
          targetid varchar(50) not null,
          sender varchar(50) not null,
          message text not null,
          opendate timestamp,
          senddate timestamp not null default now(),
          primary key(mno)
      );

      alter table tbl_message add constraint fk_usertarget foreign key(targetid) references tbl_user(uid);

      insert into tbl_user(uid,upw,uname) values('user00','user00','재훈');
      insert into tbl_user(uid,upw,uname) values('user01','user01','재훈1');
      insert into tbl_user(uid,upw,uname) values('user02','user02','재훈2');
      insert into tbl_user(uid,upw,uname) values('user03','user03','재훈3');
      insert into tbl_user(uid,upw,uname) values('user04','user04','재훈4');

### 4. UserVO, MessageVO
	public class UserVO {

		private String uid; //사용자아이디
		private String upw; // 패스워드
		private String uname; //이름
		private int upoint; //포인트
	}

	public class MessageVO {

		private Integer mid; // 메세지 구분 아이디
		private String targetid; //포인트를 보낼 아이디
		private String sender; //사용자 아이디
		private String message; //메세지
		private Date opendate; // 메세지 본 날짜
		private Date senddate;//메세지 보낸 날짜
### DAO
	public interface MessageDAO {
		//메세지 작성
		public void create(MessageVO vo) throws Exception;
		//메세지 가지고오기(조건에 맞는)
		public MessageVO readMessage(Integer mid) throws Exception;
		//업데이트
		public void updateState(Integer mid) throws Exception;
}

	public interface PointDAO {
		//포인트 증가,감소(Impl에서는 map으로 set:두개의 파라미터를 사용하니까 )
		public void updatePoint(String uid, int point) throws Exception;
}
###Mapper(Message,Point)
	<mapper namespace="com.overware.mapper.MessageMapper">

	<insert id="create">
		insert into tbl_message(targetid, sender,message) values(#{targetid},#{sender},#{message})
	</insert>
	<select id="read" resultType="MessageVO">
		select * from tbl_message where mid=#{mid}
	</select>
	<update id="updateState">
		update tbl_message set opendate=now() where mid=#{mid}
	</update>
</mapper>

	<mapper namespace="com.overware.mapper.PointMapper">
		<update id="updatePoint">
			update tbl_user set upoint= upoint+#{upoint} where uid=#{uid}
		</update>
</mapper>

###Service,ServiceImpl
	@Service
	public class MessageServiceImpl implements MessageService {

		@Inject
		private MessageDAO messageDAO;

		@Inject
		private PointDAO pointDAO;
		@Override
		public void addMessage(MessageVO vo) throws Exception {
			//새로운 매세지를 추가하면서 메세지를 보낸사람에게 10포인트를 추가함
			messageDAO.create(vo); //
			pointDAO.updatePoint(vo.getSender(), 10);

		}

		@Override
		public MessageVO readMessage(String uid, Integer mno) throws Exception {
			//메세지를 조회하고
			messageDAO.updateState(mno);
			//메세지를 조회한 사람은 포인트가 5포인트 올라가고
			pointDAO.updatePoint(uid, 5);
			//다작업한후에는 메세지를 조회해서 반환
			return messageDAO.readMessage(mno);
		}

	}
