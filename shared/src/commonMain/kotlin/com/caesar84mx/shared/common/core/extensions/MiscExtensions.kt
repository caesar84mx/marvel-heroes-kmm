package com.caesar84mx.shared.common.core.extensions

import com.caesar84mx.shared.common.data.model.Hero
import com.otto.ottocastshared.db.HeroEntity

fun HeroEntity.mapToHero(): Hero = Hero(
    id = id,
    name = name,
    realName = real_name,
    team = team,
    firstAppearance = first_appearance.toInt(),
    createdBy = created_by,
    publisher = publisher,
    avatar = avatar_url,
    bio = bio
)