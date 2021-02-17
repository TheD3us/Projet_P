package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TableModificateurTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TableModificateur.class);
        TableModificateur tableModificateur1 = new TableModificateur();
        tableModificateur1.setId(1L);
        TableModificateur tableModificateur2 = new TableModificateur();
        tableModificateur2.setId(tableModificateur1.getId());
        assertThat(tableModificateur1).isEqualTo(tableModificateur2);
        tableModificateur2.setId(2L);
        assertThat(tableModificateur1).isNotEqualTo(tableModificateur2);
        tableModificateur1.setId(null);
        assertThat(tableModificateur1).isNotEqualTo(tableModificateur2);
    }
}
