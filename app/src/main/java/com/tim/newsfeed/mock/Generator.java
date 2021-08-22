package com.tim.newsfeed.mock;

import com.tim.newsfeed.pojo.Category;
import com.tim.newsfeed.pojo.Condition;
import com.tim.newsfeed.pojo.News;
import com.tim.newsfeed.pojo.Provider;
import com.tim.newsfeed.util.Constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Generator {

    static String[] content = new String[]{"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.", "On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains."};

    public static List<News> getNewsList() {
        List<News> list = new ArrayList<>();
        List<Category> categoryList = getCategoryList();
        List<Provider> providerList = getProviderList();

        Random random = new Random();
        long aDay = TimeUnit.DAYS.toMillis(1);
        long now = new Date().getTime();
        Date startDate = new Date(now - aDay * 30);
        for (int i = 0; i < 10; i++) {
            int r = random.nextInt(1000);
            int providerNumber = random.nextInt(4);
            int categoryNumber = random.nextInt(8);
            int contentNum = random.nextInt(content.length - 1);
            Date randomDate = between(startDate, new Date());

            News mockNews = new News("Title " + r, "Sub Title " + r, "Author " + r, randomDate, content[contentNum],
                    providerList.get(providerNumber),
                    categoryList.get(categoryNumber));
            list.add(mockNews);
        }
        return list;
    }

    private static Date between(Date start, Date end) {
        long startMillis = start.getTime();
        long endMillis = end.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom.current()
                .nextLong(startMillis, endMillis);

        return new Date(randomMillisSinceEpoch);
    }

    static String[] categoryTitleList = new String[]{
            Constant.CATEGORY_CRIME,
            Constant.CATEGORY_BUSINESS,
            Constant.CATEGORY_CARS,
            Constant.CATEGORY_ENTERTAINMENT,
            Constant.CATEGORY_FAMILY,
            Constant.CATEGORY_HEALTH,
            Constant.CATEGORY_POLITICS,
            Constant.CATEGORY_RELIGION,
            Constant.CATEGORY_SCIENCE
    };
    static String[] providerTitleList = new String[]{
            Constant.PROVIDER_DAILY_TIMES,
            Constant.PROVIDER_NEWS_EXPRESS,
            Constant.PROVIDER_DAILY_BUGLE,
            Constant.PROVIDER_NEW_NEWS,
            Constant.PROVIDER_NEWS_NOW
    };

    public static List<Category> getCategoryList() {
        List<Category> categoryList = new ArrayList<>();
        for (String s : categoryTitleList) {
            Category category = new Category(s);
            categoryList.add(category);
        }
        return categoryList;
    }

    public static List<Provider> getProviderList() {
        List<Provider> providerList = new ArrayList<>();
        for (String s : providerTitleList) {
            Provider provider = new Provider(s);
            providerList.add(provider);
        }
        return providerList;
    }
}
