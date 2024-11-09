# HopeChain - Transparent Donation Tracking on the Blockchain

**HopeChain** is a decentralized donation tracking application built on the Solana blockchain. This project is dedicated to enhancing transparency and accountability in charitable donations, allowing donors to trace their contributions from the initial donation to their final use. By leveraging blockchain technology, HopeChain provides a secure, publicly verifiable, and efficient system that fosters trust between donors and non-profit organizations.

---

## Table of Contents

- [Problem It Solves](#problem-it-solves)
- [Screenshots](#screenshots)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [How It Works](#how-it-works)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [Future Roadmap](#future-roadmap)
- [Contributing](#contributing)
- [License](#license)

---

## Problem It Solves

Traditional donation systems often lack transparency, making it difficult for donors to verify how their contributions are utilized. This results in a lack of trust, reduced donor engagement, and higher audit costs for non-profits. **HopeChain** addresses these issues by:
- Providing a **verifiable donation tracking system** where each contribution is recorded immutably on the blockchain.
- Offering **real-time updates** on campaign balances and donation activity.
- **Enabling non-profits to manage campaigns transparently** and prove the impact of donations.
- Supporting potential **Solana-token Sol** to expand access to global donors and simplify cross-border contributions.

---

## Screenshots

<img src="https://github.com/user-attachments/assets/356025de-a7ac-4ca9-9566-1b97228725c6" alt="Screenshot_20241109_204057" width="250"/>
<img src="https://github.com/user-attachments/assets/e6f8278b-c1d2-4b9c-96a5-1e0887f5bc56" alt="Screenshot_20241110-012825" width="250"/>
<img src="https://github.com/user-attachments/assets/ca6dbd11-0650-46e8-95f2-cafdd20ef85c" alt="Screenshot_20241109_204323" width="250"/>
<img src="https://github.com/user-attachments/assets/84c876be-700e-460f-a76e-68a10395aa36" alt="Screenshot_20241109_204334-min" width="250"/>
<img src="https://github.com/user-attachments/assets/61f0ea86-9b91-4b37-9b12-ec9a45aa02e8" alt="Screenshot_20241109_204349" width="250"/>
<img src="https://github.com/user-attachments/assets/f39b5dd1-13bb-4df6-941a-e2919fa7841e" alt="Screenshot_20241110-012938" width="250"/>
<img src="https://github.com/user-attachments/assets/2bfdb5ab-4bc9-42b0-a6b2-60690fcbc6b8" alt="Screenshot_20241109_204602" width="250"/>

## Features

### Campaign Management
- **Transparency**: Campaigns are publicly accessible, and all donation transactions are viewable, allowing organizations to demonstrate their impact.

### Real-Time Donation Tracking
- **Transaction Feed**: Each donation is recorded on the blockchain with details such as transaction signatures and optional memos, providing full transparency for contributors.
- **Signature and Memo Polling**: The app polls Solana for new transactions to update campaigns continuously on the UI, ensuring users receive the latest campaign updates.
  
### Solflare Wallet Integration
- **Wallet Adapter Support**: Using the Mobile Wallet Adapter, HopeChain connects with Solflare wallets to allow users to donate SOL securely.
- **Secure and Seamless Wallet Interaction**: Users can easily connect and donate from their wallets directly within the app.

### Balance Conversion (Future Gemini API Integration)
- **Real-Time Conversion**: Plans to integrate with the Gemini API will allow for live conversion of SOL donations to fiat currencies, helping users see the value of their contributions.
- **Multi-Currency Support**: HopeChain aims to support donations in multiple cryptocurrencies (e.g., BTC, ETH) for global accessibility.

### User-Friendly UI
- **Jetpack Compose**: Built with Jetpack Compose, the app offers a smooth, modern Android-native experience.
- **Intuitive Layout**: A clean and straightforward design ensures ease of use for both donors and campaign managers.

### Enhanced Security
- **2FA and Biometric Options**: To protect donor funds, HopeChain will integrate additional security features like two-factor authentication and biometric verification.

---

## Tech Stack

- **Solana Blockchain**: Ensures fast, secure, and low-cost transactions, essential for handling micro-donations efficiently.
- **Kotlin & Jetpack Compose**: Provides a seamless Android-native experience with responsive, high-performance UI.
- **Mobile Wallet Adapter**: Facilitates wallet connectivity with Solflare to streamline the donation process.
- **Gemini API** (Future Integration): Enables multi-currency donations and provides real-time currency conversion for transparency in donation value.

---

## How It Works

1. **Wallet Connection**: Users connect their Solflare wallet to HopeChain using the Mobile Wallet Adapter in app.
2. **Browse Campaigns**: Donors can explore campaigns, each with detailed information about its purpose, balance, and recent donation history.
3. **Donate SOL**: With a few clicks, donors can contribute SOL directly to a chosen campaign. Each donation is recorded on the blockchain, ensuring immutability and visibility.
4. **Real-Time Tracking**: HopeChain uses a polling mechanism to fetch the latest transactions, updating campaign details in real-time. Transaction signatures and optional memos provide insight into donation use.
5. **Future Multi-Currency Donations**: With planned Gemini API support, HopeChain will allow donations in BTC, ETH, and other assets, broadening access for global donors.

---

## Setup and Installation

### 1. Using Apk

- Download and test it directly on your physical android device. Click here to download : [app-debug.zip](https://github.com/user-attachments/files/17689252/app-debug.zip)
- You will then find this apk in your file manager's - Install packages or any other folder where your third-party apks are stored when downloaded.
- You should have solflare wallet installed in your Android device to connect your wallet account with the app.

### 2. Using Android Studio

### Prerequisites
- Android Studio
- A Solflare wallet for testing on the Solana Devnet

### Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/username/HopeChain.git
   ```

2. **Open the Project in Android Studio**

3. **Set Up the Mobile Wallet Adapter**
   - Ensure that you have the Mobile Wallet Adapter SDK configured for the project.
   - Connect your Solflare wallet to test SOL transactions.

4. **Run the App**
   - Build and run the app in Android Studio.
   - Select the **Devnet** environment to simulate test transactions.

---

## Usage

1. **Open the App**: Launch HopeChain on your Android device.
2. **Connect Wallet**: Connect your Solflare wallet using the Mobile Wallet Adapter.
3. **Browse Campaigns**: Select a campaign to view details, including balance and recent donation transactions.
4. **Donate SOL**: Choose a campaign, specify the donation amount, and confirm the transaction.
5. **Track Your Donation**: View real-time updates of your donation, including transaction signatures and memos.

---

## Future Roadmap

- **Multi-Currency Donations**: Enable donations in BTC, ETH, and other major cryptocurrencies using the Gemini API.
- **Portfolio Tracking**: Allow donors to view their entire crypto donation history within the app.
- **Price Alerts & Notifications**: Notify users of significant SOL price changes.
- **Advanced Security Features**: Implement additional security measures, including biometric authentication and 2FA for donations.

---

## Contributing

Contributions are welcome! If you want to help make HopeChain even better, please follow these steps:

1. **Fork the Repository**: Start by forking the HopeChain repository.
2. **Create a Branch**: Make a new branch for your feature or bug fix.
3. **Commit Changes**: Add detailed commit messages to describe your changes.
4. **Submit a Pull Request**: Open a pull request and describe the modifications you've made.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**HopeChain** is more than just a donation app; it's a movement towards a transparent, trust-based future for philanthropy. By embracing blockchain technology, HopeChain empowers donors and charities to operate with transparency and build confidence, one donation at a time.
