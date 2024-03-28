import baseURL from "../apiConfig.js";

export function fetchSprints(projectCode, success, failure) {
  const request_options = {
    method: `GET`,
    mode: `cors`,
    cache: `no-cache`,
    credentials: `same-origin`,
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  };

  fetch(`${baseURL}/projects/${projectCode}/sprints`, request_options)
    .then((response) => response.json())
    .then((data) => success(data))
    .catch((error) => failure(error.message));
}

export const createSprint = (projectCode, newSprintData, success, failure) => {
  const request_options = {
    method: `POST`,
    cache: `no-cache`,
    credentials: `same-origin`,
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(newSprintData),
  };

  fetch(`${baseURL}/projects/${projectCode}/sprints`, request_options)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Failed to create sprint");
      }
      return response.json();
    })
    .then((data) => {
      success(data);
    })
    .catch((error) => {
      failure(error.message);
    });
};

export const updateSprintStatus = (projectCode, sprintDTO, success, failure) => {
  const request_options = {
    method: `PATCH`,
    cache: `no-cache`,
    credentials: `same-origin`,
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(sprintDTO),
  };

  fetch(`${baseURL}/projects/${projectCode}/sprints`, request_options)
    .then((response) => {
      console.log("Received response:", response);
      if (!response.ok) {
        throw new Error("Failed to update sprint status");
      }
      return response;
    })
    .then((data) => {
      success(data);
    })
    .catch((error) => {
      console.log("Error:", error);
      failure(error.message);
    });
};
