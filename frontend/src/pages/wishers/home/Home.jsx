import Header from '../../../components/header/header-purple/Header';
import Footer from '../../../components/footer/Footer';
import { useAuth } from '../../../networks/hooks/UseAuth';
import Main from './main/Main';
import MissionVision from "./mission-vision/MissionVision";
import WishJourney from "./wish-journey/WishJourney";
import WishServices from "./wish-services/WishServices";
import BecomeAGranter from "./become-a-granter/BecomeAGranter";
import CommonQuestions from "./common-questions/CommonQuestions";
import {CommonQuestionsData} from "./common-questions/CommonQuestionsData";
import React from "react";


function Home() {
    const { isLoggedIn } = useAuth();

    return (
        <div className="signup">
            {
                isLoggedIn ? (
                    <Header isLogin={true} isGrant={false}></Header>
                ) : (
                    <Header isLogin={false}></Header>
                )
            }

            <Main></Main>
            <MissionVision></MissionVision>
            <WishJourney></WishJourney>
            <WishServices></WishServices>
            <BecomeAGranter></BecomeAGranter>
            <div className='commonQuestions'>

                <div className="commonQuestionsTitle">
                    <span className="commonQuestionsTitleText">Common Questions</span>
                </div>

                {CommonQuestionsData.map(({ question, answer , options, additionalNote}, index) => (
                    <CommonQuestions key={index} question={question} answer={answer} options={options} additionalNote={additionalNote}></CommonQuestions>
                ))}
            </div>
            <Footer></Footer>
        </div>
    );
}

export default Home;
