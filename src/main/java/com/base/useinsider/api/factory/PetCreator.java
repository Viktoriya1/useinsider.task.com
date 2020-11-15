package com.base.useinsider.api.factory;

import com.base.useinsider.api.model.Category;
import com.base.useinsider.api.model.Pet;
import com.base.useinsider.api.model.StatusEnum;
import com.base.useinsider.api.model.Tag;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Collections;

import static com.base.useinsider.utils.ConfigProperties.getTestProperty;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class PetCreator {

    private static final String PHOTO_URL = getTestProperty("PHOTO_URL");
    private static final String NAME = RandomStringUtils.randomAlphabetic(10);
    private static final long RANDOM_ID = 333333333300000L;

    public static Pet defaultPet() {
        Category category = Category.builder()
                .id(RANDOM_ID)
                .name(NAME)
                .build();

        Tag tag = Tag.builder()
                .id(RANDOM_ID)
                .name(NAME)
                .build();

        return Pet.builder()
                .id(RANDOM_ID)
                .name(NAME)
                .photoUrls(Collections.singletonList(PHOTO_URL))
                .status(StatusEnum.AVAILABLE)
                .tags(Collections.singletonList(tag))
                .category(category)
                .build();
    }

    public static Pet petWithCategoryAndTag(String categoryName, String tagName) {
        Category category = Category.builder()
                .id(RANDOM_ID)
                .name(categoryName)
                .build();

        Tag tag = Tag.builder()
                .id(RANDOM_ID)
                .name(tagName)
                .build();

        return Pet.builder()
                .id(RANDOM_ID)
                .name(randomAlphabetic(10))
                .photoUrls(Collections.singletonList(PHOTO_URL))
                .status(StatusEnum.AVAILABLE)
                .tags(Collections.singletonList(tag))
                .category(category)
                .build();
    }

}
