import React from 'react';
import './wish_services.css';
import CelebratingBirthday from '../../../../assets/img/friends-celebrating-birthday.jpeg';
import KidPlayingToys from '../../../../assets/img/kid-playing-with-toys.jpeg';
import ParentsCooking from '../../../../assets/img/parents-cooking.jpeg';
import StrokingPet from '../../../../assets/img/pet-being-stroked.jpeg';
import {Link} from "react-router-dom";

function WishServices() {

    return (
        <div className='wishServices'>

            <div className='wishServicesTitle'>
                <span className='wishServicesTitleText'>Discover the Possibilities</span>
            </div>

            <div className='wishServicesContainer'>

                <div className='wishServicesContainerItem1'>
                    <img className='wishServicesContainerItemImage' src={CelebratingBirthday} alt='Celebrating Birthday' />
                    <span className='wishServicesContainerItemTitle'>Celebrate Anywhere</span>
                    <span className='wishServicesContainerItemText'>Distance won't stop the party! Delight your loved ones with Destinex to send surprise gifts on their special day.</span>
                    <Link to={'/wish-product'} className='wishServicesContainerItemLink'>Send Joy Now</Link>
                </div>

                <div className='wishServicesContainerItem2'>
                    <img className='wishServicesContainerItemImage' src={KidPlayingToys} alt='Kid Playing Toys' />
                    <span className='wishServicesContainerItemTitle'>Back-to-School Essentials</span>
                    <span className='wishServicesContainerItemText'>From school snacks to notebooks, send everything on your back-to-school list to your kids from afar.</span>
                    <Link to={'/wish-product'} className='wishServicesContainerItemLink'>Shop Now</Link>
                </div>

            </div>

            <div className='wishServicesContainer'>

                <div className='wishServicesContainerItem1'>
                    <img className='wishServicesContainerItemImage' src={ParentsCooking} alt='Parents Cooking' />
                    <span className='wishServicesContainerItemTitle'>Caring Across Miles</span>
                    <span className='wishServicesContainerItemText'>Send love, health, and care to your parents and family members, no matter the distance.</span>
                    <Link to={'/wish-product'} className='wishServicesContainerItemLink'>Send Care Package</Link>
                </div>

                <div className='wishServicesContainerItem2'>
                    <img className='wishServicesContainerItemImage' src={StrokingPet} alt='Stroking Pet' />
                    <span className='wishServicesContainerItemTitle'>Paws Across Borders</span>
                    <span className='wishServicesContainerItemText'>Stay connected with your furry friends. Send love and supplies to your pets, no matter where you are.</span>
                    <Link to={'/wish-product'} className='wishServicesContainerItemLink'>Send Pet Supplies</Link>
                </div>

            </div>

        </div>
    )
}

export default WishServices;