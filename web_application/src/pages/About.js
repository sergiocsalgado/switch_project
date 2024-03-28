import React from "react";
import "../App.css";
import teamCohesion from '../assets/images/teamCohesion.jpg';
import pedro from '../assets/images/TeamMember/Pedro.png';
import luis from '../assets/images/TeamMember/Luis.png';
import andre from '../assets/images/TeamMember/Andre.png';
import carlos from '../assets/images/TeamMember/Carlos.png';
import viviana from '../assets/images/TeamMember/Viviana.png';
import sergio from '../assets/images/TeamMember/Sergio.png';
import ana from '../assets/images/TeamMember/Ana.png';
import clara from '../assets/images/TeamMember/Clara.png';
import tiago from '../assets/images/TeamMember/Tiago.png';
import andy from '../assets/images/TeamMember/Andy.png';


const team = [
    {
        name: 'Ana Ribeiro',
        image: ana,
    },
    {
        name: 'Andy Faria',
        image: andy,
    },
    {
        name: 'André Oliveira',
        image: andre,
    },
    {
        name: 'Carlos Camboa',
        image: carlos,
    },
    {
        name: 'Clara Oliveira',
        image: clara,
    },
    {
        name: 'Pedro Tamagnini',
        image: pedro,
    },
    {
        name: 'Luís Ferreira',
        image: luis,
    },
    {
        name: 'Tiago Ribeiro',
        image: tiago,
    },
    {
        name: 'Sérgio Salgado',
        image: sergio,
    },
    {
        name: 'Viviana Moreira',
        image: viviana,
    },
];


const About = () => {
    return (
        <div className='about-page'>
            <h1 style={{padding: '20px', textAlign: 'center'}}>Our Goal</h1>
            <p style={{padding: '20px'}}> We help companies improve team management <br/>and promote better
                cohesion
                and
                performance by offering optimal tools.</p>

            <img src={teamCohesion} alt="Team Cohesion" width="1000" height="auto"
                 style={{borderRadius: '10px'}}/>

            <h1 style={{padding: '20px', textAlign: 'center'}}> Our Story </h1>
            <p style={{padding: '20px'}}> Our journey into the world of coding began in 2022 when we enrolled in
                SWitCH(), a re-qualification program for IT. <br/>As a team of 10 programming students coming
                from
                diverse areas, we are determined to design, develop, and code robust and powerful software.
                <br/>Although the road ahead may be challenging, we are committed to putting in the hard work
                and
                effort
                required to achieve our dreams - and maybe even yours ! </p>

            <h1 style={{padding: '20px', textAlign: 'center'}}> Our Team </h1>

            <div className="team">
                {team.map((member, index) => (
                    <div key={index} className="team-member">
                        <img src={member.image} alt={member.name} className="team-member-image"/>
                        <h3 className="team-member-name">{member.name}</h3>
                        <br/>
                        <br/>
                    </div>
                ))}
            </div>
        </div>
    );
};


export default About;