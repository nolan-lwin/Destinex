import React from 'react';
import './wish_journey.css';
import Wisher from '../../../../assets/img/wishers-looking-at-phone.png';
import Granter from '../../../../assets/img/granter-looking-at-phone.png';
import Recipient from '../../../../assets/img/recipient-with-her-dog.png';

function WishJourney() {

    return (
        <div className='wishJourney'>

            <div className='wishJourneyTitle'>
                <span className='wishJourneyTitleText'>Wishful Journeys</span>
            </div>

            <div className='wishJourneyContent'>

                <div className='wisherStory'>
                    <img className='wisherStoryImg' src={Wisher} alt='Wisher' />
                    <div className='wisherStoryContent'>
                        <span className='wisherStoryTitle'>Manifest your wish with ease</span>
                        <span className='wisherStoryText'>Send anything securely and swiftly to anyone worldwide through Destinex.com or in the app.</span>
                    </div>
                </div>

                <div className='granterStory'>
                    <img className='granterStoryImg' src={Granter} alt='Granter' />
                    <div className='granterStoryContent'>
                        <span className='granterStoryTitle'>See real-time updates</span>
                        <span className='granterStoryText'>Dedicated granters bring your wish to life with care. Chat as they curate and fulfill your wish.</span>
                    </div>
                </div>

                <div className='recipientStory'>
                    <img className='recipientStoryImg' src={Recipient} alt='Recipient' />
                    <div className='recipientStoryContent'>
                        <span className='recipientStoryTitle'>Deliver your wish on an ideal day</span>
                        <span className='recipientStoryText'>Select a convenient time for your recipient to receive your thoughtful delivery. Enjoy the Destinex experience.</span>
                    </div>
                </div>

            </div>

        </div>
    )
}

export default WishJourney;