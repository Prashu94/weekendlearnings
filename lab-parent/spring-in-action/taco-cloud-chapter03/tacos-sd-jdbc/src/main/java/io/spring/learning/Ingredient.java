package io.spring.learning;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.domain.Persistable;
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient implements Persistable<String> {

        @Id
        private String id;
        private String name;
        private Type type;

        @Override
        public boolean isNew() {
                return true;
        }

        public static enum Type {
            WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
        }
}
