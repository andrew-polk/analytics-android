package com.segment.analytics

import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test

class PropertiesTest {
  private lateinit var properties: Properties

  @Before
  fun setUp() {
    properties = Properties()
  }

  @Test
  fun revenue() {
    properties.putRevenue(3.14)
    Assertions.assertThat(properties.value()).isEqualTo(3.14)
  }

  @Test
  fun value() {
    properties.putRevenue(2.72)
    Assertions.assertThat(properties.value()).isEqualTo(2.72)

    properties.putValue(3.14)
    Assertions.assertThat(properties.revenue()).isEqualTo(2.72)
    Assertions.assertThat(properties.value()).isEqualTo(3.14)
  }

  @Test
  fun currency() {
    properties.putCurrency("INR")
    Assertions.assertThat(properties.currency()).isEqualTo("INR")
  }

  @Test
  fun path() {
    properties.putPath("/sources")
    Assertions.assertThat(properties.path()).isEqualTo("/sources")
  }

  @Test
  fun referrer() {
    properties.putReferrer("https://www.google.ca/")
    Assertions.assertThat(properties.referrer()).isEqualTo("https://www.google.ca/")
  }

  @Test
  fun title() {
    properties.putTitle("Sports")
    Assertions.assertThat(properties.title()).isEqualTo("Sports")
  }

  @Test
  fun url() {
    properties.putUrl("https://segment.com/docs/spec")
    Assertions.assertThat(properties.url()).isEqualTo("https://segment.com/docs/spec")
  }

  @Test
  fun name() {
    properties.putName("Stripe")
    Assertions.assertThat(properties.name()).isEqualTo("Stripe")
  }

  @Test
  fun category() {
    properties.putCategory("Sources")
    Assertions.assertThat(properties.category()).isEqualTo("Sources")
  }

  @Test
  fun sku() {
    properties.putSku("sku-code")
    Assertions.assertThat(properties.sku()).isEqualTo("sku-code")
  }

  @Test
  fun price() {
    Assertions.assertThat(properties.price()).isEqualTo(0.0)

    properties.putPrice(1.01)
    Assertions.assertThat(properties.price()).isEqualTo(1.01)
  }

  @Test
  fun productId() {
    properties.putProductId("123e4567-e89b-12d3-a456-426655440000")
    Assertions.assertThat(properties.productId()).isEqualTo("123e4567-e89b-12d3-a456-426655440000")
  }

  @Test
  fun orderId() {
    properties.putOrderId("123e4567-e89b-12d3-a456-426655440000")
    Assertions.assertThat(properties.orderId()).isEqualTo("123e4567-e89b-12d3-a456-426655440000")
  }

  @Test
  fun total() {
    Assertions.assertThat(properties.total()).isEqualTo(0.0)

    properties.putValue(3.14)
    Assertions.assertThat(properties.total()).isEqualTo(3.14)

    properties.putRevenue(2.72)
    Assertions.assertThat(properties.total()).isEqualTo(2.72)

    properties.putTotal(1.02)
    Assertions.assertThat(properties.total()).isEqualTo(1.02)
  }

  @Test
  fun subTotal() {
    properties.putSubtotal(9.99)
    Assertions.assertThat(properties.subtotal()).isEqualTo(9.99)
  }

  @Test
  fun shipping() {
    properties.putShipping(2.72)
    Assertions.assertThat(properties.shipping()).isEqualTo(2.72)
  }

  @Test
  fun tax() {
    properties.putTax(1.49)
    Assertions.assertThat(properties.tax()).isEqualTo(1.49)
  }

  @Test
  fun discount() {
    properties.putDiscount(1.99)
    Assertions.assertThat(properties.discount()).isEqualTo(1.99)
  }

  @Test
  fun coupon() {
    properties.putCoupon("segment")
    Assertions.assertThat(properties.coupon()).isEqualTo("segment")
  }

  @Test
  fun products() {
    try {
      properties.putProducts()
    } catch (e: IllegalArgumentException) {
      Assertions.assertThat(e).hasMessage("products cannot be null or empty.")
    }

    val product1 = Properties.Product("id-1", "sku-1", 1.0)
    properties.putProducts(product1)
    Assertions.assertThat(properties.products()).containsExactly(product1)

    val product2 = Properties.Product("id-2", "sku-2", 2.0)
    properties.putProducts(product1, product2)
    Assertions.assertThat(properties.products()).containsExactly(product1, product2)
  }

  @Test
  fun repeatCustomer() {
    properties.putRepeatCustomer(true)

    Assertions.assertThat(properties.isRepeatCustomer).isTrue()
  }

  @Test
  fun product() {
    val product = Properties.Product("id", "sku", 3.14)
    Assertions.assertThat(product.id()).isEqualTo("id")
    Assertions.assertThat(product.sku()).isEqualTo("sku")
    Assertions.assertThat(product.price()).isEqualTo(3.14)

    Assertions.assertThat(product.name()).isNull()
    product.putName("name")
    Assertions.assertThat(product.name()).isEqualTo("name")
  }
}