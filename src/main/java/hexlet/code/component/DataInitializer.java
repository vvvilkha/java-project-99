package hexlet.code.component;

import hexlet.code.database.entity.Label;
import hexlet.code.database.entity.TaskStatus;
import hexlet.code.database.entity.User;
import hexlet.code.database.repository.LabelRepository;
import hexlet.code.database.repository.TaskStatusRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.List;

@Component
@Profile("!test")
public class DataInitializer implements ApplicationRunner {
    private final UserDetailsManager userService;
    private final TaskStatusRepository taskStatusRepository;
    private final LabelRepository labelRepository;

    public DataInitializer(UserDetailsManager userService,
                           TaskStatusRepository taskStatusRepository,
                           LabelRepository labelRepository) {
        this.userService = userService;
        this.taskStatusRepository = taskStatusRepository;
        this.labelRepository = labelRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var user = new User();
        user.setEmail("hexlet@example.com");
        user.setPasswordDigest("qwerty");
        userService.createUser(user);

        var defaultStatuses = Map.of(
                "Draft", "draft",
                "ToReview", "to_review",
                "ToBeFixed", "to_be_fixed",
                "ToPublish", "to_publish",
                "Published", "published"
        );

        for (var entry : defaultStatuses.entrySet()) {
            if (taskStatusRepository.findBySlug(entry.getValue()).isEmpty()) {
                var status = new TaskStatus();
                status.setName(entry.getKey());
                status.setSlug(entry.getValue());
                taskStatusRepository.save(status);
            }
        }

        var defaultLabels = List.of("feature", "bug");

        for (var labelName : defaultLabels) {
            if (labelRepository.findByName(labelName).isEmpty()) {
                var label = new Label();
                label.setName(labelName);
                labelRepository.save(label);
            }
        }
    }
}
