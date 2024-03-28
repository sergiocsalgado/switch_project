import Home from "../pages/Home";
import Projects from "../pages/Projects";
import Sprints from "../pages/Sprints";
import UserStories from "../pages/UserStories";
import About from "../pages/About";
import HumanResources from "../pages/HumanResources";
import {Route as ReactRoute, Routes} from "react-router";
import Profiles from "../pages/Profiles";
import SprintBacklog from "../pages/SprintBacklog";
import ScrumBoard from "../pages/ScrumBoard"

const Route = () => {
    return (
        <Routes>
            <ReactRoute path={"/"} element={<Home/>}/>
            <ReactRoute path={"/projects"} element={<Projects/>}/>
            <ReactRoute path={"/projects/:projectCode/:status/product-backlog"} element={<UserStories/>}/>
            <ReactRoute path={"/projects/:projectCode/:status/sprints"} element={<Sprints/>}/>
            <ReactRoute path={"/projects/:projectCode/scrum-board"} element={<ScrumBoard/>}/>
            <ReactRoute path={"/projects/:projectCode/sprints/:sprintID/:sprintStatus/sprint-backlog"}
                        element={<SprintBacklog/>}/>
            <ReactRoute path={"/about"} element={<About/>}/>
            <ReactRoute path={"/profiles"} element={<Profiles/>}/>
            <ReactRoute path={"/projects/:projectCode/:status/project-team"} element={<HumanResources/>}/>
        </Routes>
    )
}

export default Route;
