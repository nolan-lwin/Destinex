import Header from '../../../components/header/header-purple/Header';
import './congrats_page.css'
import Footer from "../../../components/footer/Footer";
import congratsPic from "../../../assets/img/congrats_granter.png"
import {Link} from "react-router-dom";

function CongratsPage(){
    return(
        <div className={"congratsBody"}>
            <Header isGrant={true} isLogin={true}/>
            <div className={"congratsContentBody"}>
                <div className={"congratsPicContainer"}>
                    <img className={"congratsPic"} src={congratsPic} alt={"congratsPic"} />
                </div>
                <div className={"congratsMessageBox"}>
                    <span className={"congratsMessage"}>Congratulations, you're approved</span>
                    <span className={"congratsMessage"}>and ready to grant!</span>
                </div>
                <div className={"grantBtnBox"}>
                    <Link to={"/granter/home"} className={"congratsToGrant"}>Start Your First Grant</Link>
                </div>
            </div>
            <Footer />
        </div>
    )
}

export default CongratsPage;
