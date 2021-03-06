package io.crnk.example.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiResource;
import io.crnk.data.facet.annotation.Facet;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a move.
 */
@JsonApiResource(type = "movie")
@Entity
@Data
public class MovieEntity {

    @Id
    private UUID id;

    @JsonProperty
    @NotEmpty
    private String name;

    @Facet
    private int year;

    @OneToMany(mappedBy = "movie")
    private List<RoleEntity> roles = new ArrayList<>();

    /**
     * A link to a non-JPA resource. Served from the other side through the mappedBy-declartion.
     * Must be transient to not get persisted.
     */
    @Transient
    @JsonApiRelation(mappedBy = "movie")
    private List<Vote> votes = new ArrayList<>();

    @Version
    private Integer version;

}
