package edu.badpals.flashcards.Service;

import edu.badpals.flashcards.model.Category;
import edu.badpals.flashcards.model.Inflection;
import edu.badpals.flashcards.model.Pattern;
import edu.badpals.flashcards.model.Word;
import edu.badpals.flashcards.service.WordService;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class WordServiceTest {

    @Autowired
    WordService wordService;

    @BeforeAll
    public static void Inject(){
        Word word = new Word();
        word.setWord("comer");
        Category category = new Category();
        category.setName("verbo");
        category.addInflectionName("presente");
        category.addInflectionName("pasado");
        category.addInflectionName("futuro");
        Pattern pattern = new Pattern();
        pattern.setCategory(category);
        pattern.setPattern("er");
        Inflection inflection = new Inflection();
        inflection.setAffix("o");

    }
}
