import baseURL from '../apiConfig.js';

export function fetchScrumBoardService(projectCode, success, failure) {
    const request_options = {
        method: `GET`,
        mode: `cors`,
        cache: `no-cache`,
        credentials: `same-origin`,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    };

    fetch(`${baseURL}/projects/${projectCode}/scrum-board`, request_options)
        .then(response => response.json())
        .then(data => success(data))
        .catch(error => failure(error.message));
}
export function changeStatusService(projectCode,userStoryID,userStory,success, failure) {
    console.log("chamar")
    const request_options = {
        method: `PATCH`,
        mode: `cors`,
        cache: `no-cache`,
        credentials: `same-origin`,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            ...userStory, // Spread the userStory object
            status: userStory.status // Update the status property
        })
    };

    fetch(`${baseURL}/projects/${projectCode}/scrum-board/${userStoryID}`, request_options)
        .then(response => response.json())
        .then(data => success(data, userStory))
        .catch(error => failure(error.message));
}

