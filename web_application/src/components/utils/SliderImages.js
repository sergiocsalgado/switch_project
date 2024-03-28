import React from "react";
import SimpleImageSlider from "react-simple-image-slider";
import pedro from '../../assets/images/TeamMember/Pedro.png';
import luis from '../../assets/images/TeamMember/Luis.png';
import andre from '../../assets/images/TeamMember/Andre.png';
import carlos from '../../assets/images/TeamMember/Carlos.png';
import viviana from '../../assets/images/TeamMember/Viviana.png';
import sergio from '../../assets/images/TeamMember/Sergio.png';
import ana from '../../assets/images/TeamMember/Ana.png';
import clara from '../../assets/images/TeamMember/Clara.png';
import tiago from '../../assets/images/TeamMember/Tiago.png';
import andy from '../../assets/images/TeamMember/Andy.png';



export default function App() {
    const sliderImages = [

        {
            url: ana,
        },
        {
            url: andy,
        },
        {
            url: andre,
        },
        {
            url: carlos,
        },
        {
            url: clara,
        },
        {
            url: pedro,
        },
        {
            url: luis,
        },
        {
            url: sergio,
        },
        {
            url: tiago,
        },
        {
            url: viviana,
        },


    ];


    return (
        <div>
            <SimpleImageSlider
                width={400}
                height={400}
                images={sliderImages}
                showNavs={true}
                useGPURender={true}

                showBullets/>
        </div>
    );
}