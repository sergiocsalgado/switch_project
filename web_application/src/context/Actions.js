import { createUserStory, fetchUserStory, updateProductBacklog } from "../services/USService";
import { createProfile, fetchProfilesSer } from "../services/ProfileService";
import { createProject, fetchProjectsSer } from "../services/ProjectService";
import { createSprint, fetchSprints, updateSprintStatus } from "../services/SprintService";
import { createResources, fetchResources} from "../services/HumanResourcesService";
import {changeStatusService, fetchScrumBoardService} from "../services/ScrumBoardService";
import { fetchSprintBacklog, fromPbtoSbService} from "../services/SprintBacklogService";
import {showAlertModal} from "../components/utils/Alert";

export const SELECT_MENU = "SELECT_MENU";

//USER STORIES
//GET USER STORIES
export const FETCH_USERSTORIES_STARTED = "FETCH_USERSTORIES_STARTED";
export const FETCH_USERSTORIES_SUCCESS = "FETCH_USERSTORIES_SUCCESS";
export const FETCH_USERSTORIES_FAILURE = "FETCH_USERSTORIES_FAILURE";

export function fetchUserStoryFromProject(projectCode, dispatch) {
  dispatch(fetchUSStarted(projectCode));
  fetchUserStory(
    projectCode,
    (res) => dispatch(fetchUSSuccess(res)),
    (err) => dispatch(fetchUSFail(err.message))
  );
}

export function fetchUSStarted(projectCode) {
  return {
    type: FETCH_USERSTORIES_STARTED,
    payload: {
      projectCode: projectCode,
    },
  };
}

export function fetchUSSuccess(userStories) {
  return {
    type: FETCH_USERSTORIES_SUCCESS,
    payload: {
      data: [...userStories],
    },
  };
}

export function fetchUSFail(message) {
  return {
    type: FETCH_USERSTORIES_FAILURE,
    payload: {
      error: message,
    },
  };
}

// CREATE USER STORIES

export const CREATE_US_SUCCESS = "CREATE_US_SUCCESS";
export const CREATE_US_FAILURE = "CREATE_US_FAILURE";

export const createUSSuccess = (userStory) => {
  return {
    type: CREATE_US_SUCCESS,
    payload: userStory,
  };
};

export const createUSError = (error) => {
  return {
    type: CREATE_US_FAILURE,
    payload: error,
  };
};
export const createUSAction = (projectCode, dispatch, newUSData) => {
  createUserStory(
    projectCode,
    newUSData,
    (userStoryBody) => {
      dispatch(createUSSuccess(userStoryBody));
      showAlertModal("Success!", "Form submitted successfully!");
    },
    (error) => {
      dispatch(createUSError(error));
      showAlertModal("Error!", "Failed to create User Story. Please try again.");
    }
  );
};

//PROFILES
//GET PROFILES
export const FETCH_PROFILES_STARTED = "FETCH_PROFILES_STARTED";
export const FETCH_PROFILES_SUCCESS = "FETCH_PROFILES_SUCCESS";
export const FETCH_PROFILES_FAILURE = "FETCH_PROFILES_FAILURE";

export function fetchProfilesAction(dispatch) {
  const action = {
    type: FETCH_PROFILES_STARTED,
  };
  dispatch(action);
  fetchProfilesSer(
    (res) => dispatch(fetchProfilesSuccess(res)),
    (err) => dispatch(fetchProfilesFailure(err.message))
  );
}

export function fetchProfilesSuccess(profiles) {
  return {
    type: FETCH_PROFILES_SUCCESS,
    payload: {
      data: [...profiles],
    },
  };
}

export function fetchProfilesFailure(message) {
  return {
    type: FETCH_PROFILES_FAILURE,
    payload: {
      error: message,
    },
  };
}

// CREATE PROFILES
export const CREATE_PROFILE_SUCCESS = "CREATE_PROFILE_SUCCESS";
export const CREATE_PROFILE_FAILURE = "CREATE_PROFILE_FAILURE";

export const createProfileSuccess = (profile) => {
  return {
    type: CREATE_PROFILE_SUCCESS,
    payload: profile,
  };
};

export const createProfileError = (error) => {
  return {
    type: CREATE_PROFILE_FAILURE,
    payload: error,
  };
};

export const createProfileAction = (dispatch, newProfileData) => {
  createProfile(
    newProfileData,
    (data) => {
      console.log("action 2");
      dispatch(createProfileSuccess(data));
      showAlertModal("Success!", "Form submitted successfully!");
    },
    (error) => {
      dispatch(createProfileError(error));
      showAlertModal("Error!", "Failed to create Profile. Please try again.");
    }
  );
};

//PROJECTS
//GET PROJECTS
export const FETCH_PROJECTS_STARTED = "FETCH_PROJECTS_STARTED";
export const FETCH_PROJECTS_SUCCESS = "FETCH_PROJECTS_SUCCESS";
export const FETCH_PROJECTS_FAILURE = "FETCH_PROJECTS_FAILURE";

export function fetchProjectsAction(dispatch) {
  const action = {
    type: FETCH_PROJECTS_STARTED,
  };
  dispatch(action);
  fetchProjectsSer(
    (res) => dispatch(fetchProjectsSuccess(res)),
    (err) => dispatch(fetchProjectsFailure(err.message))
  );
}

export function fetchProjectsSuccess(projects) {
  return {
    type: FETCH_PROJECTS_SUCCESS,
    payload: {
      data: [...projects],
    },
  };
}

export function fetchProjectsFailure(message) {
  return {
    type: FETCH_PROJECTS_FAILURE,
    payload: {
      error: message,
    },
  };
}

//CREATE PROJECTS
export const CREATE_PROJECT_SUCCESS = "CREATE_PROJECT_SUCCESS";
export const CREATE_PROJECT_FAILURE = "CREATE_PROJECT_FAILURE";

export const createProjectSuccess = (project) => {
  return {
    type: CREATE_PROJECT_SUCCESS,
    payload: project,
  };
};

export const createProjectError = (error) => {
  return {
    type: CREATE_PROJECT_FAILURE,
    payload: error,
  };
};
export const createProjectAction = (dispatch, newProjectData) => {
  createProject(
    newProjectData,
    (data) => {
      console.log("action 2");
      dispatch(createProjectSuccess(data));
      showAlertModal("Success!", "Form submitted successfully!");
    },
    (error) => {
      dispatch(createProjectError(error));
      showAlertModal("Error!", "Failed to create project. Please try again.");
    }
  );
};
//HUMAN RESOURCES
//GET RESOURCES
export const FETCH_RESOURCES_STARTED = "FETCH_RESOURCES_STARTED";
export const FETCH_RESOURCES_SUCCESS = "FETCH_RESOURCES_SUCCESS";
export const FETCH_RESOURCES_FAILURE = "FETCH_RESOURCES_FAILURE";

export function fetchResourcesFormProject(projectCode, dispatch) {
  dispatch(fetchResourcesStarted(projectCode));
  fetchResources(
    projectCode,
    (res) => dispatch(fetchResourcesSuccess(res)),
    (error) => dispatch(fetchResourcesFailure(error.message))
  );
}

export function fetchResourcesStarted(projectCode) {
  return {
    type: FETCH_RESOURCES_STARTED,
    payload: {
      projectCode: projectCode,
    },
  };
}

export function fetchResourcesSuccess(resources) {
  return {
    type: FETCH_RESOURCES_SUCCESS,
    payload: {
      data: [...resources],
    },
  };
}

export function fetchResourcesFailure(message) {
  return {
    type: FETCH_RESOURCES_FAILURE,
    payload: {
      error: message,
    },
  };
}

//CREATE OF RESOURCES
// Action types
// Actions.js

export const CREATE_RESOURCES_SUCCESS = "CREATE_RESOURCES_SUCCESS"; // Action type for successful resource creation
export const CREATE_RESOURCES_FAILURE = "CREATE_RESOURCES_FAILURE"; // Action type for resource creation failure

// Action creator for successful resource creation
export const createResourcesSuccess = (resource) => {
  return {
    type: CREATE_RESOURCES_SUCCESS, // Set the action type to CREATE_RESOURCES_SUCCESS
    payload: resource, // Pass the created resource as the payload
  };
};

// Action creator for resource creation failure
export const createResourcesError = (error) => {
  return {
    type: CREATE_RESOURCES_FAILURE, // Set the action type to CREATE_RESOURCES_FAILURE
    payload: error, // Pass the error object as the payload
  };
};

// Action function for creating resources
export const createResourcesAction = (projectCode, dispatch, newResource) => {
  // Asynchronous function call to create resources
  createResources(
    projectCode,
    newResource, // Pass the new resource data
    (resourceBody) => {
      // Dispatch a success action with the created resource as the payload
      dispatch(createResourcesSuccess(resourceBody));
      showAlertModal("Success!", "Form submitted successfully!");
    },
    (error) => {
      // Dispatch an error action with the error object as the payload
      dispatch(createResourcesError(error));
      showAlertModal("Error!", "Failed to create resource. Please try again.");
    }
  );
};

//SPRINTS
//GET SPRINTS
export const FETCH_SPRINTS_STARTED = "FETCH_SPRINTS_STARTED";
export const FETCH_SPRINTS_SUCCESS = "FETCH_SPRINTS_SUCCESS";
export const FETCH_SPRINTS_FAILURE = "FETCH_SPRINTS_FAILURE";

export function fetchSprintsFromProject(projectCode, dispatch) {
  dispatch(fetchSprintsStarted(projectCode));
  fetchSprints(
    projectCode,
    (res) => dispatch(fetchSprintsSuccess(res)),
    (err) => dispatch(fetchSprintsFailure(err.message))
  );
}

export function fetchSprintsStarted(projectCode) {
  return {
    type: FETCH_SPRINTS_STARTED,
    payload: {
      projectCode: projectCode,
    },
  };
}

export function fetchSprintsSuccess(sprints) {
  return {
    type: FETCH_SPRINTS_SUCCESS,
    payload: {
      data: [...sprints],
    },
  };
}

export function fetchSprintsFailure(message) {
  return {
    type: FETCH_SPRINTS_FAILURE,
    payload: {
      error: message,
    },
  };
}

//CREATE SPRINTS
export const CREATE_SPRINT_SUCCESS = "CREATE_SPRINT_SUCCESS";
export const CREATE_SPRINT_FAILURE = "CREATE_SPRINT_FAILURE";

export const createSprintSuccess = (sprint) => {
  return {
    type: CREATE_SPRINT_SUCCESS,
    payload: sprint,
  };
};

export const createSprintError = (error) => {
  return {
    type: CREATE_SPRINT_FAILURE,
    payload: error,
  };
};

export const createSprintAction = (projectCode, dispatch, newSprintData) => {
  createSprint(projectCode, newSprintData,
      (sprintBody) => {
        dispatch(createSprintSuccess(sprintBody));
        showAlertModal("Success!", "Form submitted successfully!");
      },
      (error) => {
        dispatch(createSprintError(error));
        showAlertModal("Error!", "Failed to create sprint. Please try again.");
      }
  );
};

//Update Sprint Status
export const FETCH_UPDATE_SPRINT_STATUS_STARTED = "UPDATE_SPRINT_STATUS_STARTED";
export const FETCH_UPDATE_SPRINT_STATUS_SUCCESS = "UPDATE_SPRINT_STATUS_SUCCESS";
export const FETCH_UPDATE_SPRINT_STATUS_FAILURE = "UPDATE_SPRINT_STATUS_FAILURE";

export function fetchUpdateSprintStatusAction(projectCode, sprintID, dispatch) {
  dispatch(fetchUpdateSprintStatus(sprintID));
  return new Promise((resolve, reject) => {
    updateSprintStatus(
      projectCode,
      sprintID,
      (res) => {
        dispatch(fetchUpdateSprintStatusSuccess(res));
        // resolve the promise with the response data
        resolve(res); 
      },
      (err) => {
        dispatch(fetchUpdateSprintStatusFail(err));
        // reject the promise with the error message
        reject(err); 
      }
    );
  });
}

export function fetchUpdateSprintStatus(sprintID) {
  return {
    type: FETCH_UPDATE_SPRINT_STATUS_STARTED,
    payload: {
      sprintID: sprintID,
    },
  };
}

export function fetchUpdateSprintStatusSuccess(data) {
  return {
    type: FETCH_UPDATE_SPRINT_STATUS_SUCCESS,
    payload: data,
  };
};

export function fetchUpdateSprintStatusFail(error) {
  return {
    type: FETCH_UPDATE_SPRINT_STATUS_FAILURE,
    payload: error
  };
}

//USER STORIES IN SPRINT - SprintBacklog
//GET USER STORIES IN SPRINT
export const FETCH_USERSTORIESINSPRINT_STARTED = "FETCH_USERSTORIESINSPRINT_STARTED";
export const FETCH_USERSTORIESINSPRINT_SUCCESS = "FETCH_USERSTORIESINSPRINT_SUCCESS";
export const FETCH_USERSTORIESINSPRINT_FAILURE = "FETCH_USERSTORIESINSPRINT_FAILURE";

export function fetchUserStoryInSprint(projectCode, sprintID, dispatch) {
  dispatch(fetchUSInSprintStarted(projectCode, sprintID));
  fetchSprintBacklog(
    projectCode,
    sprintID,
    (res) => dispatch(fetchUSInSprintSuccess(res)),
    (err) => dispatch(fetchUSInSprintFail(err.message))
  );
}

export function fetchUSInSprintStarted(projectCode, sprintID) {
  return {
    type: FETCH_USERSTORIESINSPRINT_STARTED,
    payload: {
      projectCode: projectCode,
      sprintID: sprintID,
    },
  };
}

export function fetchUSInSprintSuccess(userStories) {
  return {
    type: FETCH_USERSTORIESINSPRINT_SUCCESS,
    payload: {
      data: [...userStories],
    },
  };
}

export function fetchUSInSprintFail(message) {
  return {
    type: FETCH_USERSTORIESINSPRINT_FAILURE,
    payload: {
      error: message,
    },
  };
}

//SCRUMBOARD
export const FETCH_SCRUMBOARD_STARTED = "FETCH_SCRUMBOARD_STARTED";
export const FETCH_SCRUMBOARD_SUCCESS = "FETCH_SCRUMBOARD_SUCCESS";
export const FETCH_SCRUMBOARD_FAILURE = "FETCH_SCRUMBOARD_FAILURE";

export function fetchScrumoardAction(projectCode, dispatch) {
  dispatch(fetchScrumBoardStarted(projectCode));
  fetchScrumBoardService(
    projectCode,
    (res) => dispatch(fetchScrumBoardSuccess(res)),
    (err) => dispatch(fetchScrumBoardFail(err.message))
  );
}

export function fetchScrumBoardStarted(projectCode) {
  return {
    type: FETCH_USERSTORIES_STARTED,
    payload: {
      projectCode: projectCode,
    },
  };
}

export function fetchScrumBoardSuccess(userStories) {
  return {
    type: FETCH_USERSTORIES_SUCCESS,
    payload: {
      data: [...userStories],
    },
  };
}

export function fetchScrumBoardFail(message) {
  return {
    type: FETCH_USERSTORIES_FAILURE,
    payload: {
      error: message,
    },
  };
}

//PRODUCT BACKLOG
//UPDATE PRODUCT BACKLOG WHEN SPRINT ENDS
export const FETCH_PRODUCTBACKLOG_STARTED = "FETCH_PRODUCTBACKLOG_STARTED";
export const FETCH_PRODUCTBACKLOG_SUCCESS = "FETCH_PRODUCTBACKLOG_SUCCESS";
export const FETCH_PRODUCTBACKLOG_FAILURE = "FETCH_PRODUCTBACKLOG_FAILURE";

export function fetchUpdateProductBacklogAction(projectCode, dispatch) {
  dispatch(fetchUpdProdBacklogStarted(projectCode));
  updateProductBacklog(
    projectCode,
    (res) => dispatch(fetchUpProdBacklSuccess(res)),
    (err) => dispatch(fetchUpProdBacklFail(err))
  );
}

export function fetchUpdProdBacklogStarted(projectCode) {
  return {
    type: FETCH_PRODUCTBACKLOG_STARTED,
    payload: {
      projectCode: projectCode,
    },
  };
}

export function fetchUpProdBacklSuccess(data) {
  return {
    type: FETCH_PRODUCTBACKLOG_SUCCESS,
    payload: data,
  };
}

export function fetchUpProdBacklFail(error) {
  return {
    type: FETCH_PRODUCTBACKLOG_FAILURE,
    payload: error
  };
}

//From Product Backlog to Sprint Backlog
export const PATCH_ADDTOSPRINTBACKLOG_STARTED = "PATCH_ADDTOSPRINTBACKLOG_STARTED";
export const PATCH_ADDTOSPRINTBACKLOG_SUCCESS = "PATCH_ADDTOSPRINTBACKLOG_SUCCESS";
export const PATCH_ADDTOSPRINTBACKLOG_FAILURE = "PATCH_ADDTOSPRINTBACKLOG_FAILURE";

export function patchPbToSbAction(
  projectCode,
  userStoryID,
  sprintID,
  userStory,
  dispatch
) {
  dispatch(patchPbToSbStarted(projectCode, userStoryID, sprintID, userStory));
  fromPbtoSbService(
    projectCode,
    userStoryID,
    sprintID,
    userStory,
    (res) => dispatch(patchPbToSbSuccess(res)),
    (err) => dispatch(patchPbToSbFail(err.message))
  );
}

export function patchPbToSbStarted(
  projectCode,
  userStoryID,
  sprintID,
  userStory
) {
  return {
    type: PATCH_ADDTOSPRINTBACKLOG_STARTED,
    payload: {
      projectCode: projectCode,
      userStoryID: userStoryID,
      sprintID: sprintID,
      userStory,
    },
  };
}

export function patchPbToSbSuccess(data) {
  return {
    type: PATCH_ADDTOSPRINTBACKLOG_SUCCESS,
    payload: {
      data: data,
    },
  };
}

export function patchPbToSbFail(message) {
  return {
    type: PATCH_ADDTOSPRINTBACKLOG_FAILURE,
    payload: {
      error: message,
    },
  };
}

//CHANGE STATUS USERSTORY SCRUMBOARD
export const PATCH_CHANGESTATUSSB_STARTED = "PATCH_CHANGESTATUSSB_STARTED";
export const PATCH_CHANGESTATUSSB_SUCCESS = "PATCH_CHANGESTATUSSB_SUCCESS";
export const PATCH_CHANGESTATUSSB_FAILURE = "PATCH_CHANGESTATUSSB_FAILURE";

export function changeStatusAction(projectCode, userStoryID, userStory) {
  console.log("action is called")
  return dispatch => {
    dispatch(changeStatusStarted(projectCode, userStoryID, userStory));
    changeStatusService(
        projectCode,
        userStoryID,
        userStory,
        (data, updatedUserStory) => dispatch(changeStatusSuccess(data, updatedUserStory)),
        (error) => dispatch(changeStatusFailure(error.message))
    );
    console.log("service called 2")
  };

}


export function changeStatusStarted(projectCode, userStoryID,userStory) {
  return {
    type: PATCH_CHANGESTATUSSB_STARTED,
    payload: {
      projectCode: projectCode,
      userStoryID:userStoryID,
      userStory:userStory
    },
  };
}

export function changeStatusSuccess(data, updatedUserStory) {
  console.log("vhama animal")
  return {
    type: PATCH_CHANGESTATUSSB_SUCCESS,
    payload: {
      data: data,
      updatedUserStory: updatedUserStory,
    },
  };
}

export function changeStatusFailure(error) {
  return {
    type: PATCH_CHANGESTATUSSB_FAILURE,
    payload: {
      error: error,
    },
  };
}
