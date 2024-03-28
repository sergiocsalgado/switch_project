import {
  CREATE_PROFILE_FAILURE,
  CREATE_PROFILE_SUCCESS,
  CREATE_PROJECT_FAILURE,
  CREATE_PROJECT_SUCCESS,
  CREATE_RESOURCES_FAILURE,
  CREATE_RESOURCES_SUCCESS,
  CREATE_SPRINT_FAILURE,
  CREATE_SPRINT_SUCCESS,
  CREATE_US_FAILURE,
  CREATE_US_SUCCESS,
  FETCH_PROFILES_FAILURE,
  FETCH_PROFILES_STARTED,
  FETCH_PROFILES_SUCCESS,
  FETCH_PROJECTS_FAILURE,
  FETCH_PROJECTS_STARTED,
  FETCH_PROJECTS_SUCCESS,
  FETCH_RESOURCES_FAILURE,
  FETCH_RESOURCES_STARTED,
  FETCH_RESOURCES_SUCCESS,
  FETCH_SCRUMBOARD_FAILURE,
  FETCH_SCRUMBOARD_STARTED,
  FETCH_SCRUMBOARD_SUCCESS,
  FETCH_SPRINTS_FAILURE,
  FETCH_SPRINTS_STARTED,
  FETCH_SPRINTS_SUCCESS,
  FETCH_USERSTORIES_FAILURE,
  FETCH_USERSTORIES_STARTED,
  FETCH_USERSTORIES_SUCCESS,
  FETCH_USERSTORIESINSPRINT_FAILURE,
  FETCH_USERSTORIESINSPRINT_STARTED,
  FETCH_USERSTORIESINSPRINT_SUCCESS,
  FETCH_PRODUCTBACKLOG_FAILURE,
  FETCH_PRODUCTBACKLOG_STARTED,
  FETCH_PRODUCTBACKLOG_SUCCESS,
  SELECT_MENU,
  PATCH_ADDTOSPRINTBACKLOG_STARTED,
  PATCH_ADDTOSPRINTBACKLOG_SUCCESS,
  PATCH_ADDTOSPRINTBACKLOG_FAILURE,
  FETCH_UPDATE_SPRINT_STATUS_STARTED,
  FETCH_UPDATE_SPRINT_STATUS_SUCCESS,
  FETCH_UPDATE_SPRINT_STATUS_FAILURE,
  PATCH_CHANGESTATUSSB_STARTED,
  PATCH_CHANGESTATUSSB_SUCCESS,
  PATCH_CHANGESTATUSSB_FAILURE,
} from "./Actions";

const reducer = (state, action) => {
  switch (action.type) {
    case SELECT_MENU:
      const key = action.payload.key;
      const { nav } = state;
      const menu = nav.menus.find((item) => item.key === key);
      const newNav = { ...nav, selectedMenu: menu };

      return {
        ...state,
        nav: newNav,
      };

    //USER STORIES
    case FETCH_USERSTORIES_STARTED:
      return {
        ...state,
        userStories: {
          ...state.userStories,
          loading: true,
          error: null,
          data: [],
          projectCode: action.payload.projectCode,
        },
      };
    case FETCH_USERSTORIES_SUCCESS:
      return {
        ...state,
        userStories: {
          ...state.userStories,
          loading: false,
          error: null,
          data: [...action.payload.data],
        },
      };
    case FETCH_USERSTORIES_FAILURE:
      return {
        ...state,
        userStories: {
          loading: false,
          error: action.payload.error,
          data: [],
          projectCode: "",
        },
      };
    case CREATE_US_SUCCESS:
      console.log("req 1");
      return {
        ...state,
        userStories: {
          ...state.userStories,
          loading: false,
          error: null,
          data: [...state.userStories.data, action.payload],
        },
      };
    case CREATE_US_FAILURE:
      return {
        ...state,
        userStory: null,
        loading: false,
        error: action.payload,
      };

    //PROFILES
    case FETCH_PROFILES_STARTED:
      return {
        ...state,
        profiles: {
          loading: true,
          error: null,
          data: [],
        },
      };
    case FETCH_PROFILES_SUCCESS:
      return {
        ...state,
        profiles: {
          loading: false,
          error: null,
          data: [...action.payload.data],
        },
      };
    case FETCH_PROFILES_FAILURE:
      return {
        ...state,
        profiles: {
          loading: false,
          error: action.payload.error,
          data: [...action.payload.data],
        },
      };

    case CREATE_PROFILE_SUCCESS:
      return {
        ...state,
        profiles: {
          ...state.profiles,
          loading: false,
          error: null,
          data: [...state.profiles.data, action.payload],
        },
      };
    case CREATE_PROFILE_FAILURE:
      return {
        ...state,
        profile: null,
        loading: false,
        error: action.payload,
      };

    //PROJECTS
    case FETCH_PROJECTS_STARTED:
      return {
        ...state,
        projects: {
          loading: true,
          error: null,
          data: [],
        },
      };
    case FETCH_PROJECTS_SUCCESS:
      return {
        ...state,
        projects: {
          loading: false,
          error: null,
          data: [...action.payload.data],
        },
      };
    case FETCH_PROJECTS_FAILURE:
      return {
        ...state,
        projects: {
          loading: false,
          error: action.payload.error,
          data: [],
        },
      };
    case CREATE_PROJECT_SUCCESS:
      return {
        ...state,
        projects: {
          ...state.projects,
          loading: false,
          error: null,
          data: [...state.projects.data, action.payload],
        },
      };
    case CREATE_PROJECT_FAILURE:
      return {
        ...state,
        project: null,
        loading: false,
        error: action.payload,
      };
    //RESOURCES
    case FETCH_RESOURCES_STARTED:
      return {
        ...state,
        resources: {
          ...state.resources,
          loading: true, // Indicates that resource fetching has started
          error: null, // Clear any previous errors
          data: [], // Initialize an empty array for the resource data
          projectCode: action.payload.projectCode,
        },
      };
    case FETCH_RESOURCES_SUCCESS:
      return {
        ...state,
        resources: {
          ...state.resources,
          loading: false, // Resource fetching is completed
          error: null, // Clear any previous errors
          data: [...action.payload.data], // Update resource data with the received data
        },
      };

    case FETCH_RESOURCES_FAILURE:
      return {
        ...state,
        resources: {
          ...state.resources,
          loading: false, // Resource fetching is completed
          error: action.payload.error, // Set the error with the received error
          data: [], // Update resource data with the received data
          projectCode: "",
        },
      };

    case CREATE_RESOURCES_SUCCESS:
      return {
        ...state,
        resources: {
          ...state.resources, // Preserve the existing resource state
          loading: false, // Resource creation is completed
          error: null, // Clear any previous errors
          data: [...state.resources.data, action.payload], // Add the newly created resource to the existing data
        },
      };

    case CREATE_RESOURCES_FAILURE:
      return {
        ...state,
        resources: {
          ...state.resources, // Preserve the existing resource state
          loading: false, // Resource creation is completed
          error: action.payload, // Set the error with the received error
          data: [...state.resources.data], // Preserve the existing resource data
        },
      };

    //SPRINT
    case FETCH_SPRINTS_STARTED:
      return {
        ...state,
        sprints: {
          ...state.sprints,
          loading: true,
          error: null,
          data: [],
          projectCode: action.payload.projectCode,
        },
      };

    case FETCH_SPRINTS_SUCCESS:
      return {
        ...state,
        sprints: {
          ...state.sprint,
          loading: false,
          error: null,
          data: [...action.payload.data],
        },
      };

    case FETCH_SPRINTS_FAILURE:
      return {
        ...state,
        sprints: {
          loading: false,
          error: action.payload.error,
          data: [],
          projectCode: "",
        },
      };
    case CREATE_SPRINT_SUCCESS:
      return {
        ...state,
        sprints: {
          ...state.sprints,
          loading: false,
          error: null,
          data: [...state.sprints.data, action.payload],
        },
      };

    case CREATE_SPRINT_FAILURE:
      return {
        ...state,
        sprint: null,
        loading: false,
        error: action.payload,
      };

    //Update Sprint Status
    case FETCH_UPDATE_SPRINT_STATUS_STARTED:
      return {
        ...state,
        sprintID: {
          ...state.sprintID,
          loading: true,
          error: null,
          data: [],
          sprintID: action.payload.sprintID,
          projectCode: action.payload.projectCode,
        },
      };
      case FETCH_UPDATE_SPRINT_STATUS_SUCCESS:
        return {
          ...state,
          sprintID: {
            ...state.sprintID,
            loading: false,
            error: null,
            data: action.payload,
          },
        };
    case FETCH_UPDATE_SPRINT_STATUS_FAILURE:
      return {
        ...state,
        sprintID: {
          ...state.sprintID,
          loading: false,
          error: action.payload,
          data: [],
          projectCode: "",
        },
      };

    // SPRINTBACKLOG
    case FETCH_USERSTORIESINSPRINT_STARTED:
      return {
        ...state,
        userStories: {
          ...state.userStories,
          loading: true,
          error: null,
          data: [],
          projectCode: action.payload.projectCode,
          sprintID: action.payload.sprintID,
        },
      };
    case FETCH_USERSTORIESINSPRINT_SUCCESS:
      return {
        ...state,
        userStories: {
          ...state.userStories,
          loading: false,
          error: null,
          data: [...action.payload.data],
        },
      };
    case FETCH_USERSTORIESINSPRINT_FAILURE:
      return {
        ...state,
        userStories: {
          loading: false,
          error: action.payload.error,
          data: [],
          sprintID: "",
        },
      };

    //From productBacklog to SprintBacklog
    case PATCH_ADDTOSPRINTBACKLOG_STARTED:
      return {
        ...state,
        userStories: {
          ...state.userStories,
          loading: true,
          error: null,
          data: [],
          projectCode: action.payload.projectCode,
          userStoryID: action.payload.userStoryID,
          sprintID: action.payload.sprintID,
          userStory: action.payload.userStory,
        },
      };

    case PATCH_ADDTOSPRINTBACKLOG_SUCCESS:
      return {
        ...state,
        userStories: {
          ...state.userStories,
          loading: false,
          error: null,
          userStoryID: action.payload.userStoryID,
          data: action.payload.data,
        },
      };

    case PATCH_ADDTOSPRINTBACKLOG_FAILURE:
      return {
        ...state,
        userStories: {
          ...state.userStories,
          loading: false,
          error: action.payload.error,
          data: [],
        },
      };

    //SCRUM BOARD
    case FETCH_SCRUMBOARD_STARTED:
      return {
        ...state,
        userStories: {
          ...state.userStories,
          loading: true,
          error: null,
          data: [],
          projectCode: action.payload.projectCode,
        },
      };
    case FETCH_SCRUMBOARD_SUCCESS:
      return {
        ...state,
        userStories: {
          ...state.userStories,
          loading: false,
          error: null,
          data: [...action.payload.data],
        },
      };
    case FETCH_SCRUMBOARD_FAILURE:
      return {
        ...state,
        userStories: {
          loading: false,
          error: action.payload.error,
          data: [],
          projectCode: "",
        },
      };

    //PRODUCT BACKLOG
    case FETCH_PRODUCTBACKLOG_STARTED:
      return {
        ...state,
        userStories: {
          ...state.userStories,
          loading: true,
          error: null,
          data: [],
          projectCode: action.payload.projectCode,
        },
      };
    case FETCH_PRODUCTBACKLOG_SUCCESS:
      return {
        ...state,
        userStories: {
          ...state.userStories,
          loading: false,
          error: null,
          data: action.payload.data,
        },
      };
    case FETCH_PRODUCTBACKLOG_FAILURE:
      return {
        ...state,
        userStories: {
          loading: false,
          error: action.payload.error,
          data: [],
          projectCode: "",
        },
      };

      //CHANGE STATUS SCRUM BOARD

    case PATCH_CHANGESTATUSSB_STARTED:
      return {
        ...state,
        userStories: {
          ...state.userStories,
          loading: true,
          error: null,
          data: [],
          projectCode: action.payload.projectCode,
          userStoryID: action.payload.userStoryID,
          userStory: action.payload.userStory,
        },
      };

    case PATCH_CHANGESTATUSSB_SUCCESS:
      return {
        ...state,
        userStories: {
          ...state.userStories,
          loading: false,
          error: null,
          data: action.payload.data,
        },
      };

    case PATCH_CHANGESTATUSSB_FAILURE:
      return {
        ...state,
        userStories: {
          ...state.userStories,
          loading: false,
          error: action.payload.error,
        },
      };

    default:
      return state;
  }
};

export default reducer;
