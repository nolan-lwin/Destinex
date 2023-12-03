import React from 'react';
import './mission_vision.css';
import HappyFamily from '../../../../assets/img/happy-family.jpeg';

function MissionVision() {

    return (
        <div className='missionVision'>

            <div className='missionVisionLeftContainer'>

                <div className='missionVisionLeftContainerTitle'>
                    <span className='missionVisionLeftContainerTitleText'>Bridging Hearts </span>
                    <span className='missionVisionLeftContainerTitleText'>Across Borders</span>
                </div>

                <div className='missionVisionLeftContainerContent'>
                    <span className='missionVisionLeftContainerContentText'>We envision a world where the act of making a wish becomes a universal language, and every heartfelt desire finds its way to its intended recipient. Destinex aims to be the catalyst for moments of surprise, delight, and connection, fostering a global network of wishers and granters who share in the joy of making dreams come true. Our vision is to redefine the boundaries of generosity and kindness, making every corner of the world feel a little bit closer.</span>
                </div>

            </div>

            <div className='missionVisionRightContainer'>
                <img className={'happyFamily'} src={HappyFamily} alt={'Happy Family'} />
            </div>

        </div>
    )
}

export default MissionVision;