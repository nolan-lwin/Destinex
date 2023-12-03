import React, {useState} from 'react';
import { Link } from 'react-router-dom';

import IconBellBlack from '../../../assets/svg/icon-bell-black.svg';
import DemoUser from '../../../assets/img/demo-user.png';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars } from '@fortawesome/free-solid-svg-icons';
import { faAngleDown } from '@fortawesome/free-solid-svg-icons';

import '../header.css';
import {useAuth} from "../../../networks/hooks/UseAuth";
import { getFullName } from "../../../networks/utils/AuthUtils";
import * as authUtils from "../../../networks/utils/AuthUtils";

function Header(props) {
    const [userFullName, setUserFullName] = useState(getFullName);
    const { logout } = useAuth();
    const [showDropdown, setShowDropdown] = useState(false);
    const { isGrant } = props;
    const userRole = authUtils.getRole();

    const toggleDropdown = () => {
        setShowDropdown(!showDropdown);
    }

    const handleLogout = () => {
        logout();
    }

    return (
        <div className="header-no-bg">
            <div className="left-sided">
                <div className="icon-menu-no-bg">
                    <FontAwesomeIcon icon={faBars} />
                </div>
                <div className="logo-no-bg">
                    <Link to="/" className={"link-to"}><span className='logo-destinex-no-bg'>Destinex</span></Link>
                </div>
                {!isGrant &&
                    <div className="role-option-wish-no-bg">
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
                    <div className="role-option-grant-no-bg">
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
                    <div class="divider-no-bg"></div>
                    <div class="location-no-bg">South Campus Apartment (Building 4)</div>
                    <FontAwesomeIcon icon={faAngleDown} />
                </div>
            </div>
            <div className="right-sided">
                <div className="icon-bell">
                    <img src={IconBellBlack} />
                </div>
                <div className="user-profile">
                    <div className="user-avatar">
                        <img src={DemoUser} />
                    </div>
                    <div className="username-no-bg">
                        <h5>Hello {userFullName}</h5>
                    </div>
                    <div className="user-browse-no-bg" onClick={toggleDropdown}>
                        <FontAwesomeIcon icon={faAngleDown} />
                        {showDropdown && (
                            <div className="dropdown-menu">
                                <button onClick={handleLogout}>Log out</button>
                            </div>
                        )}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Header;
