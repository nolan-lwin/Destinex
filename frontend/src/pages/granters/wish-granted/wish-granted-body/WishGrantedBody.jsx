import React, { useState } from 'react';
import './WishGrantedBody.css';
import {useNavigate} from "react-router-dom";

function WishGrantedBody () {

    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();
        navigate('/granter/home');
    }

    return (
        <form onSubmit={handleSubmit}>
            <div className='wishGrantedBody'>
                <div className={'wishGrantedTitle'}>
                    <span className={'wishGrantedTitleOne'}>Have you granted a </span>
                    <span className={'wishGrantedTitleTwo'}> Wish</span>
                    <span className={'wishGrantedTitleThree'}>?</span>
                </div>
                <div className={'wishGrantedContent'}>
                    <div className={'amountSpentBox'}>
                        <div className={'amountSpentTextBox'}>
                            <span className={'amountSpentText'}>Amount Spent (Including Tax) </span>
                        </div>
                        <input className={'amountSpent'} type={'text'}/>

                    </div>
                    <div className={'proofBox'}>
                        <span className={'proofTitle'}>Attach Receipt</span>
                        <span className={'proofDesc'}>If there’s an issue, we’ll use this receipt photo to confirm the correct wish. Please include customer name, order ID, items, prices</span>
                        <div className='attachFile'>
                            <input type='file' placeholder='Browse Files'/>
                        </div>
                    </div>
                    <div className={'proofBox'}>
                        <span className={'proofTitle'}>Attach A Drop-off Photo</span>
                        <span className={'proofDesc'}>A drop-off photo helps recipients find their packages easier and prevents miscommunication. Please upload a photo below for a proof of delivery.</span>
                        <div className='attachFile'>
                            <input type='file' placeholder='Browse Files'/>
                        </div>
                    </div>
                </div>
                <div className="submitButton">
                    <button type={'submit'} className='submitBtn'>
                        Submit
                    </button>
                </div>



            </div>
        </form>
    )
}

export default WishGrantedBody;