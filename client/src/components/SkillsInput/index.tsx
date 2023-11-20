import React, {useState} from "react";
import {WithContext as ReactTags} from 'react-tag-input';

import {ISkill} from "../../models/skills";

interface ISkillsInputProps {
    tags: ISkill[];
    suggestions: ISkill[];
    readonly: boolean;
    handleSkillAddition(tag: ISkill): void;
    handleSkillDelete(i: number): void;
    handleSkillDrag(tag: string, currPos: number, newPos: number)
}

const KeyCodes = {
    comma: 188,
    enter: 13
};

const delimiters = [KeyCodes.comma, KeyCodes.enter];

const SkillsInput: React.FC<ISkillsInputProps> = ({ tags, suggestions, readonly, handleSkillAddition, handleSkillDelete, handleSkillDrag }) => {

    const handleTagClick = index => {
        console.log('The tag at index ' + index + ' was clicked');
    };

    return <ReactTags
        tags={tags}
        suggestions={suggestions}
        delimiters={delimiters}
        handleDelete={handleSkillDelete}
        handleAddition={handleSkillAddition}
        handleDrag={handleSkillDrag}
        handleTagClick={handleTagClick}
        inputFieldPosition="bottom"
        readonly={readonly}
        autocomplete
    />
}

export default SkillsInput;