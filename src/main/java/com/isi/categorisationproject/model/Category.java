package com.isi.categorisationproject.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnore // Ignore this field during serialization
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Ignore this field during serialization
    private Set<Category> children;

    @Nullable
    private Integer level; // Dynamically calculated or stored
    private String description; // Optional description

    public Long getParentId() {
        return parent != null ? parent.getId() : null;
    }
    // Setter method for parent ID
    public void setParentId(Long parentId) {
        if (parentId != null && (parent == null || !parent.getId().equals(parentId))) {
            this.parent = new Category();
            this.parent.setId(parentId);
        } else if (parentId == null) {
            this.parent = null;
        }
    }
}
