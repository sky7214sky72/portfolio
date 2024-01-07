package org.example.portfolio.word.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWord is a Querydsl query type for Word
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWord extends EntityPathBase<Word> {

    private static final long serialVersionUID = -1572279452L;

    public static final QWord word1 = new QWord("word1");

    public final org.example.portfolio.global.domain.QBaseTimeEntity _super = new org.example.portfolio.global.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath mean = createString("mean");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public final StringPath word = createString("word");

    public QWord(String variable) {
        super(Word.class, forVariable(variable));
    }

    public QWord(Path<? extends Word> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWord(PathMetadata metadata) {
        super(Word.class, metadata);
    }

}

