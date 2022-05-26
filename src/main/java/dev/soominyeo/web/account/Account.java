package dev.soominyeo.web.account;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ACCOUNT", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"username"})
})
public class Account implements Serializable {
    public static final long versionId = 1L;

    @Id
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @GeneratedValue
    protected Long id;

    @Getter
    @Setter
    protected String email;

    @Getter
    @Setter
    @Column(nullable = false)
    protected String username;

    @Getter
    @Setter
    protected String nickname;

    @Getter
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt;

    protected Account() {
    }

    public Account(String email, String username, String nickname, @Nullable Date createdAt) {
        this.email = email;
        this.username = username;
        this.nickname = nickname;
        this.createdAt = (createdAt != null ? createdAt : new Date());
    }
}
