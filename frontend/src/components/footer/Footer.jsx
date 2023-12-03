import React from "react";
import { Link } from "react-router-dom";
import "./footer.css"

import AppstoreIcon from "../../assets/svg/appstore_icon.svg"
import FacebookIcon from "../../assets/svg/facebook_icon.svg"
import InstagramIcon from "../../assets/svg/instagram_icon.svg"
import LinkedinIcon from "../../assets/svg/linkedin_icon.svg"
import XIcon from "../../assets/svg/x_icon.svg"
import YoutubeIcon from "../../assets/svg/youtube_icon.svg"

function Footer() {
    return (
        <div className={"bottom"}>
            <div className={"bottomTitle"}>
                <Link to={'/'} className={"bottomTitleText"}>Destinex</Link>
            </div>
            <div className={"bottomLinksContainer"}>
                <img className={"AppstoreLogo"} src={AppstoreIcon} alt={"Appstore Logo"} />
                <div className={"bottomLinks"}>
                    <div className={"bottomLinksTextBox"}>
                        <span className={"bottomLinksText"}>About Destinex</span>
                        <span className={"bottomLinksText"}>Our Portfolio</span>
                        <span className={"bottomLinksText"}>Careers</span>
                        <span className={"bottomLinksText"}>Contact</span>
                        <span className={"bottomLinksText"}>Help Center</span>
                    </div>
                    <div className={"bottomLinksTextBox"}>
                        <span className={"bottomLinksText"}>Sign up to grant</span>
                        <span className={"bottomLinksText"}>Privacy Policy</span>
                        <span className={"bottomLinksText"}>Terms and Conditions</span>
                    </div>
                </div>
            </div>
            <div className={"bottomSocMed"}>
                <span className={"bottomSocMedFollowText"}>Follow Destinex</span>
                <img className={"bottomSocMedLogo"} src={FacebookIcon} alt={"Facebook Logo"} />
                <img className={"bottomSocMedLogo"} src={InstagramIcon} alt={"Instagram Logo"} />
                <img className={"bottomSocMedLogo"} src={LinkedinIcon} alt={"Linkedin Logo"} />
                <img className={"bottomSocMedLogo"} src={XIcon} alt={"X Logo"} />
                <img className={"bottomSocMedLogo"} src={YoutubeIcon} alt={"Youtube Logo"} />
            </div>
            <div className={"bottomCopyright"}>
                <span className={"bottomCopyrightText"}>@2023 Destinex. All Rights Reserved.</span>
            </div>
        </div>
    )
}

export default Footer