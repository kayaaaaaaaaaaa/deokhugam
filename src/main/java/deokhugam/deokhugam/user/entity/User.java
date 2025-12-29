package deokhugam.deokhugam.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
	name = "users",
	uniqueConstraints = {
		@UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "nickname")
	}
)
public class User {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	private UUID id;

	@Column(name = "email", nullable = false, unique = true, length = 255)
	private String email;

	@Column(name = "nickname", nullable = false, unique = true, length = 50)
	private String nickname;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@Column(name = "updated_at")
	private Instant updatedAt;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted = false;


	/* < 생성 로직 > */

	public static User create(String email, String nickname, String password) {
		User user = new User();
		user.id = UUID.randomUUID();
		user.email = email;
		user.nickname = nickname;
		user.password = password;
		user.createdAt = Instant.now();
		user.isDeleted = false;
		return user;
	}

	/* < 도메인 로직 > */

	public void updateProfile(String newNickname) {
		if (newNickname != null && !newNickname.equals(this.nickname)) {
			this.nickname = newNickname;
			touch();
		}
	}

	public void changePassword(String newPassword) {
		if (newPassword != null && !newPassword.equals(this.password)) {
			this.password = newPassword;
			touch();
		}
	}

	public void delete() {
		this.isDeleted = true;
		touch();
	}

	/* < 내부 공통 로직 - 수정시간 갱신 > */

	private void touch() {
		this.updatedAt = Instant.now();
	}
}
