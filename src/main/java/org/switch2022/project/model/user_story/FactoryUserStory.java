package org.switch2022.project.model.user_story;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.value_objects.*;

@Component
public interface FactoryUserStory {


    public UserStory createUserStory(UserStoryID userStoryID,
                                     ProjectCode projectCode,
                                     UserStoryNumber userStoryNumber,
                                     Name actor,
                                     Description userStoryText,
                                     UserStoryStatus status,
                                     Priority priority);

}
