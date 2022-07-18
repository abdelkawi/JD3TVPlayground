package com.genwin.jd3tv.screens.login.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SolanaAddress(

	@Json(name="privateKey")
	val privateKey: String? = null,

	@Json(name="address")
	val address: String? = null
)
@JsonClass(generateAdapter = true)
data class Following(

	@Json(name="fund_raisers")
	val fundRaisers: List<Any?>? = null,

	@Json(name="organizations")
	val organizations: List<Any?>? = null
)
@JsonClass(generateAdapter = true)
data class CryptoWallet(

	@Json(name="nonce")
	val nonce: String? = null
)
@JsonClass(generateAdapter = true)
data class LoginResponse(

	@Json(name="country")
	val country: String? = null,

	@Json(name="code")
	val code: String? = null,

	@Json(name="role")
	val role: String? = null,

	@Json(name="city")
	val city: String? = null,

	@Json(name="active_crypto_wallets")
	val activeCryptoWallets: List<Any?>? = null,

	@Json(name="api_token_expires_in")
	val apiTokenExpiresIn: Int? = null,

	@Json(name="crypto_wallet")
	val cryptoWallet: CryptoWallet? = null,

	@Json(name="api_token")
	val apiToken: String? = null,

	@Json(name="transaction_count")
	val transactionCount: Int? = null,

	@Json(name="created_at")
	val createdAt: String? = null,

	@Json(name="memberships")
	val memberships: List<Any?>? = null,

	@Json(name="city_name")
	val cityName: String? = null,

	@Json(name="updated_at")
	val updatedAt: String? = null,

	@Json(name="state_name")
	val stateName: String? = null,

	@Json(name="invitations")
	val invitations: List<Any?>? = null,

	@Json(name="browse_organization_as_admin")
	val browseOrganizationAsAdmin: Boolean? = null,

	@Json(name="__v")
	val V: Int? = null,

	@Json(name="solana_address")
	val solanaAddress: SolanaAddress? = null,

	@Json(name="country_name")
	val countryName: String? = null,

	@Json(name="state")
	val state: String? = null,

	@Json(name="state_id")
	val stateId: String? = null,

	@Json(name="_id")
	val id: String? = null,

	@Json(name="total_donation_value")
	val totalDonationValue: Int? = null,

	@Json(name="first_name")
	val firstName: String? = null,

	@Json(name="fund_raiser_id")
	val fundRaiserId: Any? = null,

	@Json(name="crypto_nonce")
	val cryptoNonce: String? = null,

	@Json(name="email")
	val email: String? = null,

	@Json(name="wallet_address")
	val walletAddress: String? = null,

	@Json(name="email_verified")
	val emailVerified: Boolean? = null,

	@Json(name="address")
	val address: String? = null,

	@Json(name="default_payment_method")
	val defaultPaymentMethod: Any? = null,

	@Json(name="active")
	val active: Boolean? = null,

	@Json(name="last_name")
	val lastName: String? = null,

	@Json(name="photo")
	val photo: String? = null,

	@Json(name="has_winnings")
	val hasWinnings: Boolean? = null,

	@Json(name="entries")
	val entries: Int? = null,

	@Json(name="bids_count")
	val bidsCount: Int? = null,

	@Json(name="full_name")
	val fullName: String? = null,

	@Json(name="phone_verified")
	val phoneVerified: Boolean? = null,

	@Json(name="phone")
	val phone: String? = null,

	@Json(name="following")
	val following: Following? = null,

	@Json(name="organization_id")
	val organizationId: Any? = null,

	@Json(name="key_managed_by")
	val keyManagedBy: String? = null,

	@Json(name="postal_code")
	val postalCode: String? = null,

	@Json(name="country_id")
	val countryId: String? = null,

	@Json(name="city_id")
	val cityId: String? = null
)
