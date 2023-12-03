import React from 'react';
import {Link} from "react-router-dom";
import './main.css';
import MainPhoto from '../../../../assets/img/main.png';
import WishIcon from '../../../../assets/svg/wish.svg';
import ArrowIcon from '../../../../assets/svg/arrow-right.svg';

function Main() {

    return (
        <div className='main'>

            <div className='mainLeft'>

                <div className='mainTitle'>
                    <span className='mainTitleText'>Bringing the world closer.</span>
                </div>

                <Link to={'/wish-product'} className='makeAWishBox'>

                    <div className='makeAWishIcon'>
                        <img className={"wishIcon"} src={WishIcon} alt={"Make a wish"} />
                    </div>

                    <div className='makeAWishText'>
                        <span className='makeAWishText'>Make a wish</span>
                    </div>

                    <div className='makeAWishCircle'>
                        <img className={"arrow"} src={ArrowIcon} alt={"Arrow icon"} />
                    </div>

                </Link>

            </div>

            <div className='mainRight'>
                <img className={"main"} src={MainPhoto} alt={"A man and women looking at phone"} />
            </div>

        </div>
    )
}

export default Main;