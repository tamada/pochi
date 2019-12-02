'use strict'

const initialize = () => {
  const config = {
    startOnLoad: true,
    securityLevel: 'loose'
  }
  mermaid.initialize(config)
}

const openDetails = (label) => {
  console.log(`openDetatails(${label})`)
}

window.onload = () => {
  initialize()
}