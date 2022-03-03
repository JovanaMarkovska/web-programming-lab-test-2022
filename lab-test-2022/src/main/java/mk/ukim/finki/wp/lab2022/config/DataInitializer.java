package mk.ukim.finki.wp.lab2022.config;

import mk.ukim.finki.wp.lab2022.model.NewsType;
import mk.ukim.finki.wp.lab2022.model.Role;
import mk.ukim.finki.wp.lab2022.model.User;
import mk.ukim.finki.wp.lab2022.service.NewsCategoryService;
import mk.ukim.finki.wp.lab2022.service.NewsService;
import mk.ukim.finki.wp.lab2022.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {
    public static final String ADMIN = "admin";
    public static final String USER = "user";
    private final NewsCategoryService newsCategoryService;
    private final UserService userService;
    private final NewsService service;

    public DataInitializer(NewsCategoryService newsCategoryService, UserService userService, NewsService service) {
        this.newsCategoryService = newsCategoryService;
        this.userService = userService;
        this.service = service;
    }

    private NewsType randomizeEventType(int i) {
        if (i % 3 == 0) return NewsType.DRAFT;
        else if (i % 3 == 1) return NewsType.PROMOTION;
        return NewsType.PUBLIC;
    }
    @PostConstruct
    public void initData() {
        User admin = this.userService.create(ADMIN, ADMIN, Role.ROLE_ADMIN);
        User user = this.userService.create(USER, USER, Role.ROLE_USER);

        for (int i = 1; i < 6; i++) {
            this.newsCategoryService.create("News category: " + i);
        }

        for (int i = 1; i < 11; i++) {
            this.service.create("News: " + i, "News description: " + i, 20.9 * i, this.randomizeEventType(i), this.newsCategoryService.listAll().get((i - 1) % 5).getId());
        }
    }
}
