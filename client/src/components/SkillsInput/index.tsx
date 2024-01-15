import React from "react";
import {WithContext as ReactTags} from 'react-tag-input';
import styles from './styles.module.scss';

import {ITag} from "../../models/skills";

interface ISkillsInputProps {
    tags: ITag[];
    suggestions: ITag[];
    readonly: boolean;

    handleSkillAddition(tag: ITag): void;
    handleSkillDelete(i: number): void;
    handleSkillDrag(tag: ITag, currPos: number, newPos: number)
}

const KeyCodes = {
    comma: 188,
    enter: 13
};

const delimiters = [KeyCodes.comma, KeyCodes.enter];

const SkillsInput: React.FC<ISkillsInputProps> = (
    {
        tags,
        suggestions,
        readonly,
        handleSkillAddition,
        handleSkillDelete,
        handleSkillDrag
    }) => {

    const handleTagClick = index => {
        console.log('The tag at index ' + index + ' was clicked');
    };

    return <ReactTags
        classNames={{
            tags: `ui ${styles.tags}`,
            tag: `ui ${styles.tag}`,
            tagInput: `ui input ${styles.tagsInput}`,
            selected: `${styles.selected}`,
            suggestions: `${styles.suggestedTag}`
        }}
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