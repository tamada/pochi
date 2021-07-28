'use strict'

const updateOpacityImpl = (id, className) => {
    const elements = document.getElementsByClassName(className)
    Array.from(elements).forEach(item => {
        let opacity = 0.6
        if(id == undefined || item.id == id){
            opacity = 1.0
        }
        item.style.opacity = opacity
    })
}

const updateOpacity = (id) => {
    let label = undefined
    if(id != undefined){
        label = `${id}_phase`
    }
    updateOpacityImpl(id, 'node')
    updateOpacityImpl(label, 'label')
}

const initializeEvent = (id) => {
    const elements = document.getElementsByClassName('node')
    Array.from(elements).forEach(item => {
        item.addEventListener('mouseover', () => updateOpacity(item.id))
        item.addEventListener('mouseleave', () => updateOpacity(undefined))
    })
}

const initialize = () => {
    const config = {
        startOnLoad: true,
        htmlLabels: true,
        securityLevel: 'loose',
        mermaid: {
            callback: initializeEvent
        }
    }
    mermaid.initialize(config)
}

initialize()
