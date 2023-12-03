import React, {useState} from 'react';

import IconBellWhite from '../../../assets/svg/icon-bell-white.svg';
import DemoUser from '../../../assets/img/demo-user.png';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars } from '@fortawesome/free-solid-svg-icons';
import { faAngleDown } from '@fortawesome/free-solid-svg-icons';

import '../header.css';
import {Link} from "react-router-dom";
import {useAuth} from "../../../networks/hooks/UseAuth";
import * as authUtils from "../../../networks/utils/AuthUtils";

function Header(props) {
    const { isLogin, isGrant } = props;
    const { logout } = useAuth();
    const [showDropdown, setShowDropdown] = useState(false);
    const userFullName = authUtils.getFullName();
    const userRole = authUtils.getRole();

    const toggleDropdown = () => {
        setShowDropdown(!showDropdown);
    }

    const handleLogout = () => {
        logout();
    }

    return (
        <div className="header">
            <div className="left-sided">
                <div className="icon-menu">
                    <FontAwesomeIcon icon={faBars} />
                </div>
                <div className="logo">
                    <Link className={"link-to"} to={"/"}><span className='logo-destinex'>Destinex</span></Link>
                </div>
                {!isGrant &&
                    <div className="role-option-wish">
                        <Link to={"/wish-product"}><button className="btn-wish">Wish</button></Link>
                        {userRole === "ROLE_PROVIDER" &&
                            <Link to={"/granter/home"}><button className="btn-grant">Grant</button></Link>
                        }
                        {userRole === "ROLE_USER" &&
                            <Link to={"/granter/signup"}><button className="btn-grant">Grant</button></Link>
                        }
                    </div>
                }
                {isGrant &&
                    <div className="role-option-grant">
                        <Link to={"/wish-product"}><button className="btn-wish">Wish</button></Link>
                        {userRole === "ROLE_PROVIDER" &&
                            <Link to={"/granter/home"}><button className="btn-grant">Grant</button></Link>
                        }
                        {userRole === "ROLE_USER" &&
                            <Link to={"/granter/signup"}><button className="btn-grant">Grant</button></Link>
                        }
                    </div>
                }
                <div className="location">
                    <div className="divider"></div>
                    <div className="location-text">South Campus Apartment (Building 4)</div> {/* Changed class to className */}
                    <FontAwesomeIcon icon={faAngleDown} />
                </div>
            </div>

            {isLogin ? ( // Changed to a ternary operator for better readability
                <div className="right-sided">
                    <div className="icon-bell">
                        <img src={IconBellWhite} alt="Bell Icon" /> {/* Added alt attribute */}
                    </div>
                    <div className="user-profile">
                        <div className="user-avatar">
                            <img src={DemoUser} alt="User Avatar" /> {/* Added alt attribute */}
                        </div>
                        <div className="username">
                            <h5>Hello {userFullName}</h5>
                        </div>
                        <div className="user-browse" onClick={toggleDropdown}>
                            <FontAwesomeIcon icon={faAngleDown} />
                            {showDropdown && (
                                <div className="dropdown-menu">
                                    <button onClick={handleLogout}>Log out</button>
                                </div>
                            )}
                        </div>
                    </div>
                </div>
            ) : (
                <div className="right-sided">
                    <Link to={"/signin"}><button className="sign-in">Sign in</button></Link>
                    <Link to={"/signup"}><button className="sign-up">Sign up</button></Link>
                </div>
            )}
        </div>
    );
}

export default Header;
