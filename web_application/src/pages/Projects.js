import FormProject from "../components/projects/FormProject";
import React from "react";
import FooterTables from "../components/utils/FooterTables";
import TableProj from "../components/projects/TableProj";
import '../App.css';


const Projects = () => {
    return (
        <div className="proj-container">
            <h1 style={{ marginLeft: '40px' }}>Projects</h1>
            <table>
                <TableProj />
            </table>
            <div>
                <FormProject/>
            </div>
            <FooterTables/>
        </div>
    );
};

export default Projects;