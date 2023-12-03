import React from 'react';
import './become_a_granter.css';
import GranterDeliveringPackage from '../../../../assets/img/granter-delivering-a-package.jpeg';
import {Link} from "react-router-dom";

function BecomeAGranter() {

    return (
        <div className='becomeAGranter'>

            <div className='becomeAGranterTitle'>
                <span className='becomeAGranterTitleText'>Unlocking Opportunities for Granters</span>
            </div>

            <div className='becomeAGranterContainer'>
                <div className='becomeAGranterContainerContent'>
                    <div className='becomeAGranterContainerTitle'>
                        <span className='titleText'>Sign up to grant and get paid</span>
                    </div>

                    <div className='becomeAGranterContainerText'>
                        <span className='text'>Become a Granter with Destinex, set your own schedule, and start bringing joy anytime, anywhere.</span>
                    </div>

                    <Link to={"/granter/setup"} className={'becomeAGranterContainerButton'}>Become a Granter</Link>
                </div>

                <div className='becomeAGranterContainerImage'>
                    <img className={'becomeAGranterImage'} src={GranterDeliveringPackage} alt={'Granter Delivering a Package'} />
                </div>
            </div>

        </div>
    )
}

export default BecomeAGranter;