package org.tcc.gestao.normas.core.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.tcc.gestao.normas.core.domain.model.Norma;
import org.tcc.gestao.normas.core.domain.model.NormaId;

@Component
public class NormaConverter {

    @Lazy
    @Autowired
    private ConversionService conversionService;

    @Component
    public class NormaModelToEntity implements Converter<NormaModel, Norma> {

        @Override
        public Norma convert(final NormaModel source) {
            return Norma.builder().id(NormaId.fromString(source.getId()))
            		              .codigo(source.getCodigo())
            		              .descricao(source.getDescricao())
            		              .build();
        }
    }

    @Component
    public class NormaEntityToModel implements Converter<Norma, NormaModel> {

        @Override
        public NormaModel convert(final Norma source) {
            return NormaModel.builder().id(source.getId().toString())
            		                   .codigo(source.getCodigo())
            		                   .descricao(source.getDescricao())
            		                   .build();
        }
    }

}
